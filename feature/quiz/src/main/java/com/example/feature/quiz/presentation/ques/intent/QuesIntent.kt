package com.example.feature.quiz.presentation.ques.intent

sealed interface QuesIntent {
    data object LoadQuestions : QuesIntent
    data class SelectAnswer(val questionId: String, val selectedIndex: Int) : QuesIntent
    data object SubmitAnswers : QuesIntent
}