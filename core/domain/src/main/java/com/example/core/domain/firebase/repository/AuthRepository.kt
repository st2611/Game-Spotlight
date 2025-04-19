package com.example.core.domain.firebase.repository

import com.example.core.domain.firebase.model.AuthUser

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<AuthUser>
    suspend fun signUp(email: String, password: String): Result<AuthUser>
}