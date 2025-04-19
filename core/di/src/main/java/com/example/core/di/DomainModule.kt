package com.example.core.di

import com.example.core.domain.firebase.usecase.AuthUseCase
import com.example.core.domain.firebase.usecase.ResetPasswordUseCase
import com.example.core.domain.firebase.usecase.SignInUseCase
import com.example.core.domain.firebase.usecase.SignUpUseCase
import com.example.core.domain.game.usecase.GameUseCase
import com.example.core.domain.game.usecase.GetGame
import com.example.core.domain.game.usecase.GetGames
import org.koin.dsl.module

val domainModule = module {
    single {
        GameUseCase(
            getGames = GetGames(get()),
            getGame = GetGame(get())
        )
    }

    single {
        AuthUseCase(
            signInUseCase = SignInUseCase(get()),
            signUpUseCase = SignUpUseCase(get()),
            resetPasswordUseCase = ResetPasswordUseCase(get())
        )
    }
}
