package com.example.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerialName("short_description")
    val shortDescription: String
)