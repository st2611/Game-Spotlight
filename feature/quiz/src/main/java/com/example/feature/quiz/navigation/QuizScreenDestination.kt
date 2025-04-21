package com.example.feature.quiz.navigation

sealed class QuizScreen (val route: String) {
    data object AuthScreen: QuizScreen("auth_screen")
    data object QuestionScreen: QuizScreen("question_screen")
    data object ResultScreen : QuizScreen("result_screen/{score}/{total}")
}