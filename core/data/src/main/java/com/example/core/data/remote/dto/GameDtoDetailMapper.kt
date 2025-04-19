package com.example.core.data.remote.dto

import com.example.core.domain.game.model.GameDetail

fun GameDtoDetail.toDomain(): GameDetail {
    return GameDetail(
        id = id,
        title = title,
        thumbnail = thumbnail,
        description = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        profileUrl = profileUrl
    )
}