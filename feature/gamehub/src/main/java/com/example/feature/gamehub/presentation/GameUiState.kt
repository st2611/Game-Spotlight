package com.example.feature.gamehub.presentation

import com.example.core.domain.model.Game

data class GameHubState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: String? = null
)