package ranjan.harsh.search.domain.di

import org.koin.dsl.module
import ranjan.harsh.search.domain.useCases.SearchDataUseCase

fun getSearchDomainModule() = module{
    factory{
        SearchDataUseCase(searchRepository = get())
    }
}