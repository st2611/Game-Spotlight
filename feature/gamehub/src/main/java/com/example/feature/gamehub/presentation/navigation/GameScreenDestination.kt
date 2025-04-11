package com.example.feature.gamehub.presentation.navigation

sealed class Screen(val route: String) {
    data object GameHub : Screen("game_hub")
    data object GameDetail : Screen("game_detail/{gameId}") {
        fun createRoute(gameId: Int) = "game_detail/$gameId"
    }
}

