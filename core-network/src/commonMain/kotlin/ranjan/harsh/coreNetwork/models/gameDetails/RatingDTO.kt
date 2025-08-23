package ranjan.harsh.coreNetwork.models.gameDetails

import kotlinx.serialization.Serializable

@Serializable

data class RatingDTO(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)