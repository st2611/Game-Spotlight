package com.example.core.data.firebase.source

import com.example.core.data.firebase.dto.QuestionDto

interface QuestionRemoteDataSource {
    suspend fun getQuestions(): List<QuestionDto>
}

