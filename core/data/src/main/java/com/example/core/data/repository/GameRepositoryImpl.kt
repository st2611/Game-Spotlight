package com.example.core.data.repository

import com.example.core.data.local.dao.GameDao
import com.example.core.data.local.mapper.toDomain
import com.example.core.data.local.mapper.toEntity
import com.example.core.data.remote.api.ApiService
import com.example.core.data.remote.dto.toDomain
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDetail
import com.example.core.domain.repository.GameRepository
import com.example.core.utils.logger.Logger

class GameRepositoryImpl(
    private val api: ApiService,
    private val dao: GameDao
) : GameRepository {
    override suspend fun fetchGames(): List<Game> {
        return try {
            Logger.d("Repository: Fetching from API...")
            val remoteGames = api.getGames()

            // Lưu vào Room
            Logger.d("Repository: Saving ${remoteGames.size} items to DB")
            dao.insertGames(remoteGames.map { it.toEntity() })

            // Trả về dạng domain
            remoteGames.map { it.toDomain() }

        } catch (e: Exception) {
            Logger.e("Repository: API failed, loading from local DB - ${e.localizedMessage}")
            val localGames = dao.getAllGames()
            localGames.map { it.toDomain() }
        }
    }

    override suspend fun getGameDetail(id: Int): GameDetail {
        Logger.d("Repository: Fetching game detail for id = $id")
        val dto = api.getGameDetail(id)
        return dto.toDomain()
    }
}