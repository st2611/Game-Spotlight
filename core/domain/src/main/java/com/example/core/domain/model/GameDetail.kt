package com.example.core.domain.model

data class GameDetail (
    val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val profileUrl: String
)