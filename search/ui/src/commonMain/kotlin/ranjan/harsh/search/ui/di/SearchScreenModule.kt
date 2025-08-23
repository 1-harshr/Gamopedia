package ranjan.harsh.search.ui.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ranjan.harsh.search.ui.SearchScreenViewModel

fun getSearchUiModule() = module {
    viewModel {
        SearchScreenViewModel(
            searchDataUseCase = get()
        )
    }

}