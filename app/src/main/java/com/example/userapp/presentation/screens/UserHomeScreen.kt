package com.example.userapp.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.userapp.data.AppError
import com.example.userapp.domain.model.User
import com.example.userapp.presentation.common.CustomErrorMessageProvider
import com.example.userapp.presentation.common.MainScreenScaffold
import com.example.userapp.presentation.common.NetworkErrorMessageProvider
import com.example.userapp.presentation.common.UnknownErrorMessageProvider
import com.example.userapp.presentation.components.UserCard
import com.example.userapp.ui.theme.ScreenPreview


data class UserScreenState(
    val isLoading: Boolean,
    val data: List<User>?,
    val fetchingError: AppError?,

    )

@Composable
fun UserHomeScreen(modifier: Modifier = Modifier, userScreenState: UserScreenState) {
    MainScreenScaffold(
        modifier, userScreenState.isLoading,
        errorMessageProvider =
        when (userScreenState.fetchingError) {
            is AppError.CustomAppError -> {
                CustomErrorMessageProvider()
            }
            is AppError.NetworkError -> {
                NetworkErrorMessageProvider()
            }

            is AppError.UnknownAppError -> {
                UnknownErrorMessageProvider()
            }

            null -> CustomErrorMessageProvider()
        },
    ) {
        userScreenState.data?.let { UserList(it) }

    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users) { user ->
            UserCard(user = user)
        }
    }
}


@ScreenPreview()
@Composable
fun UserListPreview() {
    val sampleUsers = listOf(
        User(
            avatar = "https://reqres.in/img/faces/1-image.jpg",
            email = "george.bluth@reqres.in",
            firstName = "George",
            id = 1,
            lastName = "Bluth"
        ),
        User(
            avatar = "https://reqres.in/img/faces/2-image.jpg",
            email = "janet.weaver@reqres.in",
            firstName = "Janet",
            id = 2,
            lastName = "Weaver"
        ),
        User(
            avatar = "https://reqres.in/img/faces/3-image.jpg",
            email = "emma.wong@reqres.in",
            firstName = "Emma",
            id = 3,
            lastName = "Wong"
        )
    )
    MaterialTheme {
        UserList(users = sampleUsers)
    }
}