package com.abanoub.cities.feature.cities.data.local.datasource

import com.abanoub.cities.feature.cities.data.local.dto.CityDto

interface CitiesDataSource {

    /**
     * Searches for cities with the given prefix.
     *
     * @param prefix The prefix to search for. The search is case-insensitive.
     * @return A list of [CityDto] objects representing cities that start with the given prefix.
     */
    suspend fun searchCities(prefix: String): List<CityDto>
}