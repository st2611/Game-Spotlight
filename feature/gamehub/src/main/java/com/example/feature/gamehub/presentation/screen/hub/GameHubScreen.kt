package com.example.feature.gamehub.presentation.screen.hub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.utils.logger.Logger
import com.example.feature.gamehub.presentation.intent.GameHubIntent
import com.example.feature.gamehub.presentation.viewmodel.GameHubViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun GameHubScreen(
    viewModel: GameHubViewModel,
    onGameClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (state.games.isEmpty() && !state.isLoading) {
            viewModel.dispatch(GameHubIntent.LoadGames)
        }
    }

    val pullRefreshState = rememberPullToRefreshState()

    Box (
        modifier = Modifier
            .fillMaxSize()
            .pullToRefresh(
                isRefreshing = state.isRefreshing,
                onRefresh = { viewModel.dispatch(GameHubIntent.RefreshGames)},
                state = pullRefreshState
            )
    ) {
        when {
            state.isLoading || state.isRefreshing -> {
                Logger.d("Game is loading")
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Error: ${state.error}",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Button(onClick = { viewModel.dispatch(GameHubIntent.LoadGames) }) {
                            Text("Load game again")
                        }
                    }
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(state.games) { game ->
                        GameItem(game, onClick = { onGameClick(game.id) })
                    }
                }
            }
        }
    }
}



