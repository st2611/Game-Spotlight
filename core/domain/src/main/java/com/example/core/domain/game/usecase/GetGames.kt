package com.example.core.domain.game.usecase

import com.example.core.domain.game.repository.GameRepository

class GetGames(
    private val repository: GameRepository
) {
    suspend operator fun invoke() = repository.fetchGames()
}