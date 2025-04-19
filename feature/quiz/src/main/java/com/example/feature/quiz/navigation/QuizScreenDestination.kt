package com.example.feature.quiz.navigation

sealed class QuizScreen (val route: String) {
    data object Auth: QuizScreen("auth_screen")
    data object Question: QuizScreen("question_screen")
}