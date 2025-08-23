package ranjan.harsh.game.domain.repository

import ranjan.harsh.game.domain.model.Game

interface GameRepository {
    suspend fun getGames(): Result<List<Game>>
}