package com.example.feature.quiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.quiz.presentation.auth.screen.AuthScreen
import com.example.feature.quiz.presentation.ques.screen.QuestionScreen
import com.example.feature.quiz.presentation.ques.screen.ResultScreen

@Composable
fun QuizScreenNavigation(
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = QuizScreen.AuthScreen.route) {
        composable(QuizScreen.AuthScreen.route) {
            AuthScreen(navController = navController)
        }

        composable(QuizScreen.QuestionScreen.route) {
            QuestionScreen(navController = navController)
        }

        composable(QuizScreen.ResultScreen.route) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            val total = backStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0

            ResultScreen(
                score = score,
                total = total,
                onRetry = {
                    navController.popBackStack(QuizScreen.QuestionScreen.route, inclusive = false)
                },
                onSignOut = {
                    navController.navigate(QuizScreen.AuthScreen.route) {
                        popUpTo(QuizScreen.QuestionScreen.route) { inclusive = true }
                    }
                }
            )
        }
    }
}