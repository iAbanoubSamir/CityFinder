package com.abanoub.cities.feature.cities.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanoub.cities.core.common.result.Result
import com.abanoub.cities.core.common.result.asUiText
import com.abanoub.cities.feature.cities.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CitiesViewModel(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : ViewModel() {

    // TODO: Use Explicit Backing Fields When K2 Mode went Stable
    private var _uiState = MutableStateFlow(CitiesUiState())
    val uiState: StateFlow<CitiesUiState> get() = _uiState

    init {
        /**
         * Perform initial search with an empty query to load all cities.
         */
        onEvent(CitiesUiEvent.SearchCities(""))
    }

    fun onEvent(event: CitiesUiEvent) {
        when (event) {
            is CitiesUiEvent.SearchCities -> searchCities(event.prefix)
        }
    }

    private fun searchCities(prefix: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        searchCitiesUseCase(prefix).collect { result ->
            when (result) {
                is Result.Error -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = result.error.asUiText()
                    )
                }

                is Result.Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        cities = result.data
                    )
                }
            }
        }
    }
}

sealed class CitiesUiEvent {
    data class SearchCities(val prefix: String) : CitiesUiEvent()
}