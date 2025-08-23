package ranjan.harsh.game.ui.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ranjan.harsh.game.ui.game.GameViewModel
import ranjan.harsh.game.ui.gameDetails.GameDetailsViewModel

fun getGameUiModule() = module {
    viewModel {
        GameViewModel(
            getGameUseCase = get()
        )
    }

    viewModel {
        GameDetailsViewModel(
            getGameDetailsUseCase = get()
        )
    }


}