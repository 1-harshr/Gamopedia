package ranjan.harsh.game.data.repository

import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.coreNetwork.apiService.ApiService
import ranjan.harsh.game.data.mappers.toDomainListOfGames
import ranjan.harsh.game.domain.repository.GameRepository

class GameRepositoryImpl(
    private val apiService: ApiService
): GameRepository {
    override suspend fun getGames(): Result<List<Game>> {
        val response = apiService.getGames()

        if(response.isSuccess){
            return Result.success(response.getOrThrow().results.toDomainListOfGames())
        }else{
            return Result.failure(response.exceptionOrNull()!!)
        }

    }
}