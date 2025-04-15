package com.example.feature.gamehub.presentation.intent

sealed interface GameHubIntent {
    data object LoadGames : GameHubIntent
    data object RefreshGames : GameHubIntent
    data class LoadGame(val id: Int) : GameHubIntent
}
