package com.example.feature.quiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.quiz.presentation.auth.screen.AuthScreen
import com.example.feature.quiz.presentation.ques.screen.QuestionScreen

@Composable
fun QuizScreenNavigation(
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = QuizScreen.Auth.route) {
        composable(QuizScreen.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(QuizScreen.Question.route) {
            QuestionScreen(navController = navController)
        }
    }
}