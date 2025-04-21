package com.example.core.data.firebase.dto

data class QuestionDto(
    val id: String = "",
    val question: String = "",
    val options: List<String> = emptyList(),
    val correctIndex: Int = 0
)