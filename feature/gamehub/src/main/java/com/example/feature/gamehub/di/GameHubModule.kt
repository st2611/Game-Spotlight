package com.example.feature.gamehub.di

import com.example.feature.gamehub.presentation.GameHubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameHubModule = module {
    viewModel { GameHubViewModel(get()) }
}