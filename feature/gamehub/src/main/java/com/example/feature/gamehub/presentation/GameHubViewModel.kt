package com.example.feature.gamehub.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetGamesUseCase
import com.example.core.utils.logger.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameHubViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GameHubState())
    val state: StateFlow<GameHubState> = _state.asStateFlow()

    fun dispatch(intent: GameHubIntent) {
        when (intent) {
            is GameHubIntent.LoadGames -> loadGames()
        }
    }

    private fun loadGames() {
        viewModelScope.launch {
            Logger.d("ViewModel: Loading games...")
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val games = getGamesUseCase()
                Logger.d("ViewModel: Loaded ${games.size} games")
                _state.value = _state.value.copy(isLoading = false, games = games)
            } catch (e: Exception) {
                Logger.e("ViewModel: Error loading games: ${e.message}")
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}