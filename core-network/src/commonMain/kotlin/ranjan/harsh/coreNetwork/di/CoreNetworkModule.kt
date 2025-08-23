package ranjan.harsh.coreNetwork.di

import org.koin.dsl.module
import ranjan.harsh.coreNetwork.apiService.ApiService
import ranjan.harsh.coreNetwork.client.KtorClient

fun getCoreNetworkModule() = module {
    single {
        ApiService(httpClient = KtorClient.getInstance())
    }
}