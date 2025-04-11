package com.example.core.data.remote.api

import com.example.core.data.remote.dto.GameDto
import com.example.core.data.remote.dto.GameDtoDetail

interface ApiService {
    suspend fun getGames(): List<GameDto>
    suspend fun getGameDetail(id: Int): GameDtoDetail
}