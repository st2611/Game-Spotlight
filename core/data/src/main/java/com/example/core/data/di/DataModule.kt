package com.example.core.data.di

import com.example.core.data.remote.api.ApiService
import com.example.core.data.remote.api.ApiServiceImpl
import com.example.core.data.remote.provider.provideKtorClient
import com.example.core.data.repository.GameRepositoryImpl
import com.example.core.domain.repository.GameRepository
import org.koin.dsl.module

val dataModule = module {
    single<GameRepository> { GameRepositoryImpl(get()) } // get() = ApiService
    single { provideKtorClient() }
    single<ApiService> { ApiServiceImpl(get()) }
}