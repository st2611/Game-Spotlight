package com.example.core.data.remote.api

import com.example.core.data.remote.dto.GameDto

interface ApiService {
    suspend fun getGames(): List<GameDto>
}