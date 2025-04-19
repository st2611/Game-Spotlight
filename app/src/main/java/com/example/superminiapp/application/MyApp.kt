package com.example.superminiapp.application

import android.app.Application
import com.example.core.data.di.dataModule
import com.example.core.di.provideAllKoinModule
import com.example.feature.gamehub.di.gameHubModule
import com.example.feature.quiz.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(provideAllKoinModule() + gameHubModule + dataModule + authModule)
        }
    }
}
