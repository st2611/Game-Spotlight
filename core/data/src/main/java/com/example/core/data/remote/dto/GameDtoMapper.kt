package com.example.core.data.remote.dto

import com.example.core.domain.model.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = id,
        title = title,
        thumbnail = thumbnail,
        description = shortDescription
    )
}