package com.example.core.domain.firebase.usecase

data class AuthUseCase(
    val signInUseCase: SignInUseCase,
    val signUpUseCase: SignUpUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase
)