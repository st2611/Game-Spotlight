package com.example.core.domain.firebase.firestore.usecase

import com.example.core.domain.firebase.firestore.model.Question
import com.example.core.domain.firebase.firestore.repository.QuestionRepository

class GetQuestionsUseCase(
    private val repository: QuestionRepository
) {
    suspend operator fun invoke(): List<Question> {
        return repository.getQuestions()
    }
}
