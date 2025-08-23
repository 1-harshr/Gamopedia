package ranjan.harsh.game.domain.di

import org.koin.dsl.module
import ranjan.harsh.game.domain.useCases.GetGameDetailsUseCase
import ranjan.harsh.game.domain.useCases.GetGameUseCase

fun getGameDomainModule() = module {
    factory {
        GetGameUseCase(
            gameRepository = get()
        )
    }

    factory {
        GetGameDetailsUseCase(
            gameRepository = get()
        )
    }


}