package com.example.core.data.remote.api

import com.example.core.data.remote.dto.GameDto
import com.example.core.data.remote.dto.GameDtoDetail
import com.example.core.utils.logger.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getGames(): List<GameDto> {
        Logger.d("API: Calling getGames endpoint")
        val response = client.get(BASE_URL) {
            accept(ContentType.Application.Json)
        }
        Logger.d("API: Status = ${response.status}")

        return response.body<List<GameDto>>()
    }

    override suspend fun getGameDetail(id: Int): GameDtoDetail {
        Logger.d("API: Calling getGameDetail for id = $id")

        val response = client.get(DETAIL_URL) {
            accept(ContentType.Application.Json)
            url {
                parameters.append("id", id.toString())
            }
        }

        Logger.d("API: Detail Status = ${response.status}")
        return response.body<GameDtoDetail>()
    }

    companion object {
        private const val BASE_URL = "https://www.freetogame.com/api/games"
        private const val DETAIL_URL = "https://www.freetogame.com/api/game"
    }
}