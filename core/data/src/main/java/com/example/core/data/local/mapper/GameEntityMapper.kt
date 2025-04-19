package com.example.core.data.local.mapper

import com.example.core.data.local.entity.GameEntity
import com.example.core.data.remote.dto.GameDto
import com.example.core.domain.game.model.Game

fun GameDto.toEntity(): GameEntity = GameEntity(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription
)

fun GameEntity.toDomain(): Game = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    description = shortDescription
)
