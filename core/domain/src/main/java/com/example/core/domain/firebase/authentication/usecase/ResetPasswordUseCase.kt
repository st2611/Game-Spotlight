package com.example.core.domain.firebase.authentication.usecase

import com.example.core.domain.firebase.authentication.repository.AuthRepository

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) =
        authRepository.resetPassword(email)
}