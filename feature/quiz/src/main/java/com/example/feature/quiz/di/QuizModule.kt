package com.example.feature.quiz.di

import com.example.feature.quiz.presentation.auth.viewmodel.AuthViewModel
import com.example.feature.quiz.presentation.ques.viewmodel.QuesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { QuesViewModel(get()) }
}