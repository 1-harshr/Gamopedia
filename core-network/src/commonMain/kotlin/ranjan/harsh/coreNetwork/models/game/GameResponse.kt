package ranjan.harsh.coreNetwork.models.game

import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val results: List<Result>,
)