package ranjan.harsh.search.data.di

import org.koin.dsl.module
import ranjan.harsh.search.data.repositiory.SearchRepositoryImpl

fun getSearchDataModule() = module {
    factory { SearchRepositoryImpl(apiService = get()) }
}