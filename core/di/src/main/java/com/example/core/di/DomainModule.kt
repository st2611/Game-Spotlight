package com.example.core.di

import com.example.core.domain.usecase.GameUseCase
import com.example.core.domain.usecase.GetGame
import com.example.core.domain.usecase.GetGames
import org.koin.dsl.module

val domainModule = module {
    single {
        GameUseCase(
            getGames = GetGames(get()),
            getGame = GetGame(get())
        )
    }
}
