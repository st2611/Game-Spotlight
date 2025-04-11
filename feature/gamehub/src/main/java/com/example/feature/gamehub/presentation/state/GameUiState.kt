package com.example.feature.gamehub.presentation.state

import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail

data class GameHubState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val selectedGame: GameDetail? = null,
    val error: String? = null
)