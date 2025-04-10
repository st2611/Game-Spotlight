package com.example.core.di

import com.example.core.domain.usecase.GetGamesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetGamesUseCase(get()) }
}
