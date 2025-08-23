package ranjan.harsh.game.ui.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ranjan.harsh.game.ui.game.GameViewModel

fun getGameUiModule() = module {
    viewModel {
        GameViewModel(
            getGameUseCase = get()
        )
    }

}