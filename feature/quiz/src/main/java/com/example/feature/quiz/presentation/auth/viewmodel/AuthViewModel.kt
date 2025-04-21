package com.example.feature.quiz.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.firebase.authentication.usecase.AuthUseCase
import com.example.feature.quiz.presentation.auth.intent.AuthIntent
import com.example.feature.quiz.presentation.auth.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.EmailChanged -> {
                _state.value = _state.value.copy(email = intent.email)
            }

            is AuthIntent.PasswordChanged -> {
                _state.value = _state.value.copy(password = intent.password)
            }

            AuthIntent.SignInClicked -> signIn()
            AuthIntent.SignUpClicked -> signUp()
            AuthIntent.ResetPassword -> resetPassword()
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            val result = authUseCase.signInUseCase(state.value.email, state.value.password)
            _state.value = when {
                result.isSuccess -> _state.value.copy(
                    isLoading = false,
                    isSignInSuccess = true,
                    email = "",
                    password = ""
                )

                else -> _state.value.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message,
                    email = "",
                    password = ""
                )
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            val result = authUseCase.signUpUseCase(state.value.email, state.value.password)
            _state.value = when {
                result.isSuccess -> _state.value.copy(
                    isLoading = false,
                    isSignUpSuccess = true,
                    email = "",
                    password = ""
                )

                else -> _state.value.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message,
                    email = "",
                    password = ""
                )
            }
        }
    }

    private fun resetPassword() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            val result = authUseCase.resetPasswordUseCase(state.value.email)

            _state.value = when {
                result.isSuccess -> _state.value.copy(
                    isLoading = false,
                    message = "Password reset email sent!",
                    email = ""
                )

                else -> _state.value.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }
}