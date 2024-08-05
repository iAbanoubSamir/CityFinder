package com.abanoub.cities.feature.cities.domain.usecase

import com.abanoub.cities.feature.cities.domain.repository.CityRepository

class SearchCitiesUseCase(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(query: String) = cityRepository.searchCities(query)
}