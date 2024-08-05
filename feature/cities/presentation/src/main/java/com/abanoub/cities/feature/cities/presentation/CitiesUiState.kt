package com.abanoub.cities.feature.cities.presentation

import com.abanoub.cities.core.ui.UiText
import com.abanoub.cities.feature.cities.domain.model.City

data class CitiesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: UiText = UiText.DynamicString(""),
    val cities: List<City> = emptyList(),
)
