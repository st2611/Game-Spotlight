package com.example.core.domain.game.repository

import com.example.core.domain.game.model.Game
import com.example.core.domain.game.model.GameDetail

interface GameRepository {
    suspend fun fetchGames(): List<Game>
    suspend fun getGameDetail(id: Int): GameDetail
}