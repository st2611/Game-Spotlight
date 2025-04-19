package com.example.feature.quiz.presentation.auth.state

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignInSuccess: Boolean = false,
    val isSignUpSuccess: Boolean = false,
    val error: String? = null
)
