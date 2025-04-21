package com.example.core.domain.firebase.firestore.repository

import com.example.core.domain.firebase.firestore.model.Question

interface QuestionRepository {
    suspend fun getQuestions(): List<Question>
}
