package com.example.feature.gamehub.presentation

sealed interface GameHubIntent {
    data object LoadGames : GameHubIntent
}