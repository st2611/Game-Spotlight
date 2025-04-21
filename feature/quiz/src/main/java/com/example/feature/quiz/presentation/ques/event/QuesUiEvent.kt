package com.example.feature.quiz.presentation.ques.event

sealed interface QuesUiEvent {
    data class NavigateToResult(val score: Int, val total: Int) : QuesUiEvent
}
