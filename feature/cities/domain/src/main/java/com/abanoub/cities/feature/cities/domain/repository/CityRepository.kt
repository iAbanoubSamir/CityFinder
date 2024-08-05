package com.abanoub.cities.feature.cities.domain.repository

import com.abanoub.cities.core.common.result.DataError
import com.abanoub.cities.core.common.result.Result
import com.abanoub.cities.feature.cities.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    /**
     * Searches for cities based on the provided prefix.
     * If the prefix is empty, returns all cities.
     *
     * @param prefix The prefix to search for city names.
     * @return A [Flow] emitting the result which contains a list of [City] objects or an error.
     */
    suspend fun searchCities(prefix: String): Flow<Result<List<City>, DataError>>
}