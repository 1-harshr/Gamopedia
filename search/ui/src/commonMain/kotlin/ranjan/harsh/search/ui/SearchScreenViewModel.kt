package ranjan.harsh.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ranjan.harsh.common.domain.models.Game
import ranjan.harsh.search.domain.useCases.SearchDataUseCase

@OptIn(FlowPreview::class)
class SearchScreenViewModel(
    private val searchDataUseCase: SearchDataUseCase
): ViewModel() {


    init {
        viewModelScope.launch {
            _query.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce { 500 }
                .collectLatest { q ->
                    search(q)
                }
        }
    }

    private val _state = MutableStateFlow(SearchScreen.UiState())
    val state = _state.asStateFlow()

    private val _query = MutableStateFlow("")

    fun onQueryChange(query: String){
        _query.update { query }
    }

    private fun search(q: String) = searchDataUseCase(_query.value)
        .onStart {
            _state.value = _state.value.copy(isLoading = true)
        }
        .onEach { result ->
            result.onSuccess { games ->
                _state.value = _state.value.copy(games = games)
            }.onFailure {
                _state.value = _state.value.copy(error = it.message.toString())
            }
        }.launchIn(viewModelScope)
}

data object SearchScreen{

    data class UiState(
        val games: List<Game>? = null,
        val isLoading: Boolean = false,
        val error: String = ""
    )
}