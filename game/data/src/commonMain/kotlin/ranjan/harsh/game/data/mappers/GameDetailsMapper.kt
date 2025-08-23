package ranjan.harsh.game.data.mappers

import ranjan.harsh.coreNetwork.models.gameDetails.DeveloperDTO
import ranjan.harsh.coreNetwork.models.gameDetails.GameDetailsResponse
import ranjan.harsh.coreNetwork.models.gameDetails.PlatformXDTO
import ranjan.harsh.coreNetwork.models.gameDetails.StoreDTO
import ranjan.harsh.coreNetwork.models.gameDetails.TagDTO
import ranjan.harsh.game.data.mappers.toPlatformDomain
import ranjan.harsh.game.domain.model.Developer
import ranjan.harsh.game.domain.model.GameDetails
import ranjan.harsh.game.domain.model.Platform
import ranjan.harsh.game.domain.model.Store
import ranjan.harsh.game.domain.model.Tag

fun GameDetailsResponse.toGameDetailsDomain() = GameDetails(
    name = this.name,
    id = this.id,
    description = this.description,
    backgroundImage = this.background_image,
    additionalImage = this.background_image_additional,
    platforms = this.platforms.toPlatformDomain(),
    stores = this.stores.toStoreDomain(),
    developers = this.developers.toDeveloperDomain(),
    tags = this.tags.toTagDomain()
)

private fun List<PlatformXDTO>.toPlatformDomain() = this.map {
    Platform(
        name = it.platform.name,
        image = it.platform.image_background
    )
}

private fun List<StoreDTO>.toStoreDomain() = this.map {
    Store(
        name = it.store.name,
        image = it.store.image_background,
        gameCount = it.store.games_count,
        domain = it.store.domain
    )
}

private fun List<DeveloperDTO>.toDeveloperDomain() = this.map {
    Developer(
        name = it.name,
        image = it.image_background,
        gameCount = it.games_count
    )
}

private fun List<TagDTO>.toTagDomain() = this.map {
    Tag(
        name = it.name,
        image = it.image_background
    )
}


