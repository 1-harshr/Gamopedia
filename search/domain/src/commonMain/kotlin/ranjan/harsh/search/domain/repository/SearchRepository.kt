package ranjan.harsh.search.domain.repository

import ranjan.harsh.common.domain.models.Game

interface SearchRepository {
    suspend fun search(query: String): Result<List<Game>>
}