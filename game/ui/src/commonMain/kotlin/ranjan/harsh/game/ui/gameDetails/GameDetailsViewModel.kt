package ranjan.harsh.game.ui.gameDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import ranjan.harsh.game.domain.model.GameDetails
import ranjan.harsh.game.domain.useCases.GetGameDetailsUseCase

class GameDetailsViewModel (
    private val getGameDetailsUseCase: GetGameDetailsUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(GameDetailsScreenState.UiState())
    val uiState = _uiState.asStateFlow()

    fun getGameDetails(id: Int) = getGameDetailsUseCase(id)
        .onStart {
            _uiState.update {
                GameDetailsScreenState.UiState(isLoading = false)
            }
        }.onEach {
            it.onSuccess { gameDetails ->
                _uiState.update {
                    GameDetailsScreenState.UiState(gameDetails = gameDetails)
                }
            }.onFailure { error ->
                _uiState.update {
                    GameDetailsScreenState.UiState(error = "Something Went Wrong")
                }
            }
        }.launchIn(viewModelScope)
}

object GameDetailsScreenState{

    data class UiState(
        val isLoading: Boolean = false,
        val gameDetails: GameDetails? = null,
        val error: String = ""
    )

}