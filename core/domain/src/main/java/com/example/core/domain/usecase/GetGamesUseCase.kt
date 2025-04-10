package com.example.core.domain.usecase

import com.example.core.domain.repository.GameRepository

class GetGamesUseCase(
    private val repository: GameRepository
) {
    suspend operator fun invoke() = repository.fetchGames()
}