package ranjan.harsh.game.data.repository

import ranjan.harsh.common.data.mappers.toDomainListOfGames
import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.coreNetwork.apiService.ApiService
import ranjan.harsh.coreNetwork.models.gameDetails.GameDetailsResponse
import ranjan.harsh.game.data.mappers.toGameDetailsDomain
import ranjan.harsh.game.domain.model.GameDetails
import ranjan.harsh.game.domain.repository.GameRepository

class GameRepositoryImpl(
    private val apiService: ApiService
): GameRepository {
    override suspend fun getGames(): Result<List<Game>> {
        val response = apiService.getGames()

        return if(response.isSuccess){
            Result.success(response.getOrThrow().results.toDomainListOfGames())
        }else{
            Result.failure(response.exceptionOrNull()!!)
        }
    }

    override suspend fun getGameDetails(id: Int): Result<GameDetails> {
        val response = apiService.getGameDetails(id)

        return if(response.isSuccess){
            Result.success(response.getOrThrow().toGameDetailsDomain())
        }else{
            Result.failure(response.exceptionOrNull()!!)
        }

    }


}