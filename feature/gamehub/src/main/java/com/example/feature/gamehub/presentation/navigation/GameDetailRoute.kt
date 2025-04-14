package com.example.feature.gamehub.presentation.navigation

import androidx.activity.compose.BackHandler
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.feature.gamehub.presentation.intent.GameHubIntent
import com.example.feature.gamehub.presentation.screen.GameDetailScreen
import com.example.feature.gamehub.presentation.viewmodel.GameHubViewModel


@Composable
fun GameDetailRoute(
    backStackEntry: NavBackStackEntry,
    viewModel: GameHubViewModel,
    navController: NavHostController
) {
    val gameId = backStackEntry.arguments?.getInt("gameId") ?: return

    // Trigger load game detail
    LaunchedEffect(gameId) {
        viewModel.dispatch(GameHubIntent.LoadGame(gameId))
    }

    val state by viewModel.state.collectAsState()
    val game = state.selectedGame

    BackHandler {
        viewModel.resetGameDetail()
        navController.popBackStack()
    }

    if (game != null) {
        GameDetailScreen(
            game = game,
            onBackClick = {
                viewModel.resetGameDetail()
                navController.popBackStack()
            }
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