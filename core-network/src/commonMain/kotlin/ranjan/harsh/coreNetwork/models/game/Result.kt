package ranjan.harsh.coreNetwork.models.game

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val backgroundImage: String,
    val id: Int,
    val name: String,
)