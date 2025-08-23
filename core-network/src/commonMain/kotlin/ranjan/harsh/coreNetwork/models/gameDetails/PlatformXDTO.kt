package ranjan.harsh.coreNetwork.models.gameDetails

import kotlinx.serialization.Serializable

@Serializable

data class PlatformXDTO(
    val platform: PlatformXXDTO,
    val released_at: String?,
)