package com.example.core.domain.firebase.authentication.usecase

import com.example.core.domain.firebase.authentication.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signIn(email, password)
}