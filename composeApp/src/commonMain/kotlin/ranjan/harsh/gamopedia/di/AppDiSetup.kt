package ranjan.harsh.gamopedia.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import ranjan.harsh.coreNetwork.di.getCoreNetworkModule
import ranjan.harsh.game.data.di.getGameDataModule
import ranjan.harsh.game.domain.di.getGameDomainModule
import ranjan.harsh.game.ui.di.getGameUiModule
import ranjan.harsh.search.data.di.getSearchDataModule
import ranjan.harsh.search.domain.di.getSearchDomainModule
import ranjan.harsh.search.ui.di.getSearchUiModule

fun initKoin(
    koinApplication: ((KoinApplication) -> Unit)? = null
) {
    startKoin {
        modules(
            getGameDataModule(),
            getCoreNetworkModule(),
            getGameDomainModule(),
            getGameUiModule(),
            getGameDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),
            getSearchDataModule()
        )
    }
}