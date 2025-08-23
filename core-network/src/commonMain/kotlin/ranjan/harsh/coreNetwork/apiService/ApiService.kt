package ranjan.harsh.coreNetwork.apiService

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ranjan.harsh.coreNetwork.models.game.GameResponse

class ApiService(
    val httpClient: HttpClient
) {
    suspend fun getGames() :  Result<GameResponse> {
        return try{
            val response = httpClient.get("api/games") {
                url{
                    parameter("key", "1abb1867f52548a4aa9f54dd4946af2f")
                }
            }.body<GameResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun search(q: String): Result<GameResponse> {
        return try{
            val response = httpClient.get("api/games") {
                url{
                    parameter("key", "1abb1867f52548a4aa9f54dd4946af2f")
                    parameter("search", q)
                }
            }.body<GameResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun getGameDetails(id: Int){
        try{
            val response = httpClient.get("api/games/$id"){
                url{
                    parameter("key", "1abb1867f52548a4aa9f54dd4946af2f")
                }
            }.body<GameResponse>()
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}