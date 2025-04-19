package com.example.core.domain.game.usecase

import com.example.core.domain.game.repository.GameRepository

class GetGame(
    private val repository: GameRepository
) {
    suspend operator fun invoke(id: Int) = repository.getGameDetail(id)
}