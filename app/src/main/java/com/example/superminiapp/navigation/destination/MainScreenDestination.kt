package com.example.superminiapp.navigation.destination

sealed class MainScreenDestination(val route: String) {
    data object Selector : MainScreenDestination("selector")
    data object GameHub : MainScreenDestination("game_hub")
    data object Quiz : MainScreenDestination("quiz")
}
