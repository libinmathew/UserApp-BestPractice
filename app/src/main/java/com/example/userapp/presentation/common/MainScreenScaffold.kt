package com.example.userapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.userapp.data.AppError
import kotlinx.coroutines.launch

interface ErrorMessageProvider {
    fun getMessage(error: AppError): String
}


class CustomErrorMessageProvider : ErrorMessageProvider {
    override fun getMessage(error: AppError): String = when (error) {
        is AppError.CustomAppError -> "Custom Error: ${error.message}"
        else -> "Unknown Error"
    }
}

class NetworkErrorMessageProvider : ErrorMessageProvider {
    override fun getMessage(error: AppError): String = when (error) {
        is AppError.NetworkError.ServerError -> "Server Error: ${error.message}"
        is AppError.NetworkError.TimeoutError -> "Timeout Error: ${error.message}"
        is AppError.NetworkError.UnauthorizedError -> "Unauthorized Error: ${error.message}"
        is AppError.NetworkError.UnknownError -> "Unknown Network Error: ${error.message}"
        else -> "Unknown Network Error"
    }
}

class UnknownErrorMessageProvider : ErrorMessageProvider {
    override fun getMessage(error: AppError): String = when (error) {
        is AppError.UnknownAppError -> "Unknown App Error: ${error.message}"
        else -> "Unknown Error"
    }
}

@Composable
fun MainScreenScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    fetchError: AppError? = null,
    updateError: AppError? = null,
    loadingView: @Composable (Modifier) -> Unit = { LoadingView(modifier) },
    errorView: @Composable (AppError, ErrorMessageProvider) -> Unit =
        { error, errorProvider ->
            ErrorView(error, errorProvider)
        },
    errorMessageProvider: ErrorMessageProvider,
    snackBarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier.fillMaxSize()) {
        content()
        if (isLoading) {
            loadingView(Modifier)
        }
        if (fetchError != null) {
            errorView(fetchError, errorMessageProvider)
        }
        if (updateError != null) {
            LaunchedEffect(updateError) {
                coroutineScope.launch {
                    snackBarHostState?.showSnackbar(
                        message = errorMessageProvider.getMessage(
                            updateError
                        )
                    )
                }
            }
        }
    }
}

// Loading View
@Composable
fun LoadingView(modifier: Modifier = Modifier, color: Color = Color(0xFF7788DA)) {
    Box(modifier.fillMaxSize()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center), color = color)
    }
}

// Error View
@Composable
fun ErrorView(
    error: AppError,
    errorMessageProvider: ErrorMessageProvider
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = errorMessageProvider.getMessage(error),
        )
    }
}





