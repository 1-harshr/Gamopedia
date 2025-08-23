package ranjan.harsh.game.domain.repository

import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.coreNetwork.models.gameDetails.GameDetailsResponse
import ranjan.harsh.game.domain.model.GameDetails


interface GameRepository {
    suspend fun getGames(): Result<List<Game>>

    suspend fun getGameDetails(id: Int): Result<GameDetails>
}