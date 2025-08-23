package ranjan.harsh.game.data.mappers

import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.coreNetwork.models.game.Result

fun List<Result>.toDomainListOfGames(): List<Game> = map{
    Game(
        id = it.id,
        title = it.name,
        imageUrl = it.background_image
    )
}