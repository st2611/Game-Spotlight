package com.example.superminiapp.navigation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.gamehub.presentation.navigation.GameScreenNavigation
import com.example.feature.quiz.navigation.QuizScreenNavigation
import com.example.superminiapp.navigation.destination.MainScreenDestination

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainScreenDestination.Selector.route) {
        composable(MainScreenDestination.Selector.route) {
            MainSelectorScreen(navController)
        }

        composable(MainScreenDestination.GameHub.route) {
            GameScreenNavigation()
        }

        composable(MainScreenDestination.Quiz.route) {
            QuizScreenNavigation()
        }
    }
}
