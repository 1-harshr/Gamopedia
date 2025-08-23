package ranjan.harsh.gamopedia.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import ranjan.harsh.coreNetwork.di.getCoreNetworkModule
import ranjan.harsh.game.data.di.getGameDataModule
import ranjan.harsh.game.domain.di.getGameDomainModule
import ranjan.harsh.game.ui.di.getGameUiModule

fun initKoin(
    koinApplication: ((KoinApplication) -> Unit)? = null
) {
    startKoin {
        modules(
            getGameDataModule(),
            getCoreNetworkModule(),
            getGameDomainModule(),
            getGameUiModule(),
        )
    }
}