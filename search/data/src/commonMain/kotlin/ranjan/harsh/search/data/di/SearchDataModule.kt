package ranjan.harsh.search.data.di

import org.koin.dsl.module
import ranjan.harsh.search.data.repositiory.SearchRepositoryImpl
import ranjan.harsh.search.domain.repository.SearchRepository

fun getSearchDataModule() = module {
    factory<SearchRepository> { SearchRepositoryImpl(apiService = get()) }
}