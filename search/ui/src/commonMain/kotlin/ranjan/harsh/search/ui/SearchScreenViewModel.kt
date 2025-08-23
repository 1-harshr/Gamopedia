package ranjan.harsh.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.search.domain.useCases.SearchDataUseCase

@OptIn(FlowPreview::class)
class SearchScreenViewModel(
    private val searchDataUseCase: SearchDataUseCase
): ViewModel() {


    private val _state = MutableStateFlow(SearchScreen.UiState())
    val state = _state.asStateFlow()

    private val _query = MutableStateFlow("")

    fun onQueryChange(query: String){
        _query.update { query }
        search(query)
    }

    private fun search(q: String) = searchDataUseCase(_query.value)
        .onStart {
            _state.update {
                SearchScreen.UiState(isLoading = true)
            }
        }
        .onEach { result ->
            result.onSuccess { games ->
                _state.update {
                    SearchScreen.UiState(games = games)
                }
            }.onFailure { error ->
                _state.update {
                    SearchScreen.UiState(error = "No Result Found")
                }
            }
        }.launchIn(viewModelScope)
}

object SearchScreen{

    data class UiState(
        val games: List<Game>? = null,
        val isLoading: Boolean = false,
        val error: String = ""
    )
}