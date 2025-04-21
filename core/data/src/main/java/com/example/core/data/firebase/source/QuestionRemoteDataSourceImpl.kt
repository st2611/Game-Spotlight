package com.example.core.data.firebase.source

import com.example.core.data.firebase.dto.QuestionDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class QuestionRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore
) : QuestionRemoteDataSource {
    override suspend fun getQuestions(): List<QuestionDto> = suspendCoroutine { cont ->
        firestore.collection("questions")
            .get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.documents.mapNotNull { it.toObject(QuestionDto::class.java) }
                cont.resume(list)
            }
            .addOnFailureListener { cont.resumeWithException(it) }
    }
}
