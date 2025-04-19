package com.example.core.domain.firebase.usecase

import com.example.core.domain.firebase.repository.AuthRepository

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) =
        authRepository.resetPassword(email)
}