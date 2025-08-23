package ranjan.harsh.game.domain.repository

import ranjan.harsh.common.domain.models.Game


interface GameRepository {
    suspend fun getGames(): Result<List<Game>>
}