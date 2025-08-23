package ranjan.harsh.game.domain.useCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ranjan.harsh.game.domain.model.GameDetails
import ranjan.harsh.game.domain.repository.GameRepository

class GetGameDetailsUseCase(
    private val gameRepository: GameRepository
) {

    operator fun invoke(id: Int) = flow<Result<GameDetails>> {
        emit(gameRepository.getGameDetails(id))
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Default)

}