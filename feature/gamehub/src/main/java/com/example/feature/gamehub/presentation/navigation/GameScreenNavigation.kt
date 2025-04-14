package com.example.feature.gamehub.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.gamehub.presentation.screen.GameHubScreen
import com.example.feature.gamehub.presentation.viewmodel.GameHubViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreenNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: GameHubViewModel = koinViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.GameHub.route) {
        composable(Screen.GameHub.route) {
            GameHubScreen(
                viewModel = viewModel,
                onGameClick = { gameId ->
                    navController.navigate(Screen.GameDetail.createRoute(gameId))
                }
            )
        }

        composable(
            route = Screen.GameDetail.route,
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) { backStackEntry ->
            GameDetailRoute(
                backStackEntry = backStackEntry,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

