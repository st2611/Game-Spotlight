package com.example.feature.gamehub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GameUseCase
import com.example.core.utils.logger.Logger
import com.example.feature.gamehub.presentation.intent.GameHubIntent
import com.example.feature.gamehub.presentation.state.GameHubState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameHubViewModel(
    private val gameUseCase: GameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GameHubState())
    val state: StateFlow<GameHubState> = _state.asStateFlow()

    fun dispatch(intent: GameHubIntent) {
        when (intent) {
            is GameHubIntent.LoadGames -> loadGames()
            is GameHubIntent.LoadGame -> loadGame(intent.id)
        }
    }

    private fun loadGames() {
        viewModelScope.launch {
            Logger.d("ViewModel: Loading games...")
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val games = gameUseCase.getGames()
                Logger.d("ViewModel: Loaded ${games.size} games")
                _state.value = _state.value.copy(isLoading = false, games = games)
            } catch (e: Exception) {
                Logger.e("ViewModel: Error loading games: ${e.message}")
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun loadGame(id: Int) {
        viewModelScope.launch {
            Logger.d("ViewModel: Loading game detail for ID $id")
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val game = gameUseCase.getGame(id)
                Logger.d("ViewModel: Loaded game detail: ${game.title}")
                _state.value = _state.value.copy(isLoading = false, selectedGame = game)
            } catch (e: Exception) {
                Logger.e("ViewModel: Error loading game detail: ${e.message}")
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun resetGameDetail() {
        _state.value = _state.value.copy(selectedGame = null)
    }
}