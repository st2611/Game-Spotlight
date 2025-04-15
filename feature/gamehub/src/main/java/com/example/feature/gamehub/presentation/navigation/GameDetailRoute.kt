package com.example.feature.gamehub.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    when{
        state.isGameDetailLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        game != null -> {
            GameDetailScreen(
                game = game,
                onBackClick = {
                    viewModel.resetGameDetail()
                    navController.popBackStack()
                }
            )
        }

        state.gameDetailError != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Failed to load game detail: ${state.gameDetailError ?: "Unknown error"}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        viewModel.dispatch(GameHubIntent.LoadGame(gameId))
                    }) {
                        Text("Try Again")
                    }
                }
            }
        }
    }
}