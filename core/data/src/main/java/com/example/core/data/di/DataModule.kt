package com.example.core.data.di

import androidx.room.Room
import com.example.core.data.local.database.GameDatabase
import com.example.core.data.remote.api.ApiService
import com.example.core.data.remote.api.ApiServiceImpl
import com.example.core.data.remote.provider.provideKtorClient
import com.example.core.data.repository.AuthRepositoryImpl
import com.example.core.data.repository.GameRepositoryImpl
import com.example.core.domain.firebase.repository.AuthRepository
import com.example.core.domain.game.repository.GameRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<GameRepository> { GameRepositoryImpl(get(), get()) }
    single { provideKtorClient() }
    single<ApiService> { ApiServiceImpl(get()) }

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = GameDatabase::class.java,
            name = "games_db"
        ).build()
    }

    single { get<GameDatabase>().gameDao() }

    single { FirebaseAuth.getInstance() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}