package com.example.feature.quiz.presentation.ques.state

import com.example.core.domain.firebase.firestore.model.Question

data class QuesState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val selectedAnswers: Map<String, Int> = emptyMap(),
    val score: Int? = null,
    val error: String? = null
)
