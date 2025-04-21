package com.example.core.data.repository

import com.example.core.data.firebase.source.QuestionRemoteDataSource
import com.example.core.domain.firebase.firestore.model.Question
import com.example.core.domain.firebase.firestore.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val questionRemoteDataSource: QuestionRemoteDataSource
): QuestionRepository {
    override suspend fun getQuestions(): List<Question> {
        return questionRemoteDataSource.getQuestions().map {
            Question(
                id = it.id,
                question = it.question,
                options = it.options,
                correctIndex = it.correctIndex
            )
        }
    }
}