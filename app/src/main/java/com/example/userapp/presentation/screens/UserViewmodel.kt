package com.example.userapp.presentation.screens;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.data.DataState
import com.example.userapp.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject
constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {
    private val _userScreenState = MutableStateFlow(
        UserScreenState(
            isLoading = false,
            fetchingError = null,
            data = null,
        )
    )
    val userScreenState: StateFlow<UserScreenState> = _userScreenState.asStateFlow()
    fun getUserData() {
        viewModelScope.launch {
            _userScreenState.update { state ->
                state.copy(isLoading = true)
            }
            getUserUseCase.invoke().collect {
                when (it) {
                    is DataState.Failure -> {
                        _userScreenState.update { state ->
                            state.copy(
                                isLoading = false,
                                fetchingError = state.fetchingError,
                            )
                        }

                    }
                    is DataState.Success -> {
                        _userScreenState.update { state ->
                            state.copy(
                                isLoading = false,
                                fetchingError = null,
                                data = it.data
                            )
                        }

                    }
                }

            }
        }

    }

}