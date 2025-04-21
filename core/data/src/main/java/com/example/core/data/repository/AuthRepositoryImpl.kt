package com.example.core.data.repository

import com.example.core.domain.firebase.authentication.model.AuthUser
import com.example.core.domain.firebase.authentication.repository.AuthRepository
import com.example.core.utils.logger.Logger
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): Result<AuthUser> =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val user = it.user
                    Logger.d("AuthRepository: signIn SUCCESS: uid=${user?.uid}, email=${user?.email}")
                    cont.resume(Result.success(AuthUser(user!!.uid, user.email)))
                }
                .addOnFailureListener {
                    Logger.e("AuthRepository: signIn FAILED: ${it.message}")
                    cont.resume(Result.failure(it))
                }
        }

    override suspend fun signUp(email: String, password: String): Result<AuthUser> =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val user = it.user
                    Logger.d("AuthRepository: signUp SUCCESS: uid=${user?.uid}, email=${user?.email}")
                    cont.resume(Result.success(AuthUser(user!!.uid, user.email)))
                }
                .addOnFailureListener {
                    Logger.e("AuthRepository: signUp FAILED: ${it.message}")
                    cont.resume(Result.failure(it))
                }
        }

    override suspend fun resetPassword(email: String): Result<Unit> =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    Logger.d("AuthRepository: reset password SUCCESS:")
                    cont.resume(Result.success(Unit))
                }
                .addOnFailureListener {
                    Logger.e("AuthRepository: reset password FAILED: ${it.message}")
                    cont.resume(Result.failure(it))
                }
        }
}