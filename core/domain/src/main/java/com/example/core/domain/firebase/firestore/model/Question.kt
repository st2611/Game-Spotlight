package com.example.core.domain.firebase.firestore.model

data class Question(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)
