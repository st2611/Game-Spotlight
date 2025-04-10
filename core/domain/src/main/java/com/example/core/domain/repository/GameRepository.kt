package com.example.core.domain.repository

import com.example.core.domain.model.Game

interface GameRepository {
    suspend fun fetchGames(): List<Game>
}