package com.example.core.domain.firebase.authentication.usecase

import com.example.core.domain.firebase.authentication.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signUp(email, password)
}