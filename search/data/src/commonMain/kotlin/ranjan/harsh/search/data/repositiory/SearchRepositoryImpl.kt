package ranjan.harsh.search.data.repositiory

import ranjan.harsh.common.data.mappers.toDomainListOfGames
import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.coreNetwork.apiService.ApiService
import ranjan.harsh.search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val apiService: ApiService
): SearchRepository {

    override suspend fun search(query: String): Result<List<Game>> {
        return try{
            val response = apiService.search(query)
            if(response.isSuccess){
                Result.success(response.getOrThrow().results.toDomainListOfGames())
            }
            else{
                Result.failure(response.exceptionOrNull()!!)
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}