package ranjan.harsh.game.data.di

import org.koin.dsl.module
import ranjan.harsh.game.data.repository.GameRepositoryImpl
import ranjan.harsh.game.domain.repository.GameRepository

fun getGameDataModule() = module {
    factory<GameRepository>{
        GameRepositoryImpl(
            apiService = get()
        )
    }
}