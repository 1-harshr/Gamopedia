package ranjan.harsh.search.domain.useCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import ranjan.harsh.search.domain.repository.SearchRepository

class SearchDataUseCase(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(q: String) = flow {
        emit(searchRepository.search(q))
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.Default)
}