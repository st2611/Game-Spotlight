package com.example.feature.gamehub.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.feature.gamehub.presentation.viewmodel.GameHubViewModel
import org.koin.dsl.module

val gameHubModule = module {
    viewModel { GameHubViewModel(get()) }
}