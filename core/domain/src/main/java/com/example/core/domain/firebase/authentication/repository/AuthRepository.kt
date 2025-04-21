package com.example.core.domain.firebase.authentication.repository

import com.example.core.domain.firebase.authentication.model.AuthUser

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<AuthUser>
    suspend fun signUp(email: String, password: String): Result<AuthUser>
    suspend fun resetPassword(email: String): Result<Unit>
}