package com.abanoub.cities.feature.cities.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abanoub.cities.feature.cities.domain.model.City
import kotlinx.serialization.Serializable

@Serializable
data object Cities

fun NavGraphBuilder.citiesScreen(
    onCityClick: (City) -> Unit
) {
    composable<Cities> {
        CitiesRoute()
    }
}

fun NavController.navigateToCities() {
    navigate(Cities)
}