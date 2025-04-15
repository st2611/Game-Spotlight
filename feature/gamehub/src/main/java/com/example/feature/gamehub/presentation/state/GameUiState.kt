package com.example.feature.gamehub.presentation.state

import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail

data class GameHubState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String? = null,

    //Game Detail
    val selectedGame: GameDetail? = null,
    val isGameDetailLoading: Boolean = false,
    val gameDetailError: String? = null
)
