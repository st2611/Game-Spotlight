package com.example.feature.gamehub.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.gamehub.presentation.intent.GameHubIntent
import com.example.feature.gamehub.presentation.screen.GameDetailScreen
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
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: return@composable

            // Trigger load game detail
            LaunchedEffect(gameId) {
                viewModel.dispatch(GameHubIntent.LoadGame(gameId))
            }

            val state by viewModel.state.collectAsState()
            val game = state.selectedGame

            if (game != null) {
                GameDetailScreen(
                    game = game,
                    onBackClick = { navController.popBackStack() }
                )
            } else if (state.isLoading) {
                // Optional loading screen
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (state.error != null) {
                // Optional error screen
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error loading game detail: ${state.error}")
                }
            }
        }
    }
}
