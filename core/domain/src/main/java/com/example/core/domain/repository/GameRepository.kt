package com.example.core.domain.repository

import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail

interface GameRepository {
    suspend fun fetchGames(): List<Game>
    suspend fun getGameDetail(id: Int): GameDetail
}