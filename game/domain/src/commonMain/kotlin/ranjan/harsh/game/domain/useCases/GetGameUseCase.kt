package ranjan.harsh.game.domain.useCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ranjan.harsh.game.domain.model.Game
import ranjan.harsh.game.domain.repository.GameRepository

class GetGameUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke() = flow<Result<List<Game>>>{
        emit(gameRepository.getGames())
    }.catch {error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Default)
}