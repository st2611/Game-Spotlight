package com.example.core.domain.usecase

import com.example.core.domain.repository.GameRepository

class GetGame(
    private val repository: GameRepository
) {
    suspend operator fun invoke(id: Int) = repository.getGameDetail(id)
}