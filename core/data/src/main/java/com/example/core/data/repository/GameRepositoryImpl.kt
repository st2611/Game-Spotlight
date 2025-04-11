package com.example.core.data.repository

import com.example.core.data.remote.api.ApiService
import com.example.core.data.remote.dto.toDomain
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail
import com.example.core.domain.repository.GameRepository
import com.example.core.utils.logger.Logger

class GameRepositoryImpl(
    private val api: ApiService
) : GameRepository {
    override suspend fun fetchGames(): List<Game> {
        Logger.d("Repository: Fetching from API...")
        val result = api.getGames()
        Logger.d("Repository: Received ${result.size} items")
        return result.map {
            Logger.d("Repository: Mapping item ${it.id} - ${it.title}")
            it.toDomain()
        }
    }

    override suspend fun getGameDetail(id: Int): GameDetail {
        Logger.d("Repository: Fetching game detail for id = $id")
        val dto = api.getGameDetail(id)
        return dto.toDomain()
    }
}