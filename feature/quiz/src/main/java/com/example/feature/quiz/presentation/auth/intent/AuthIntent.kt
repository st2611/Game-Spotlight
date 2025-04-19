package com.example.feature.quiz.presentation.auth.intent

sealed interface AuthIntent {
    data class EmailChanged(val email: String) : AuthIntent
    data class PasswordChanged(val password: String) : AuthIntent
    data object SignInClicked : AuthIntent
    data object SignUpClicked : AuthIntent
}
