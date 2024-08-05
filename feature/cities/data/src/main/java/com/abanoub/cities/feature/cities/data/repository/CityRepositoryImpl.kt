package com.abanoub.cities.feature.cities.data.repository

import com.abanoub.cities.core.common.result.DataError
import com.abanoub.cities.core.common.result.Result
import com.abanoub.cities.feature.cities.data.local.datasource.CitiesDataSource
import com.abanoub.cities.feature.cities.data.local.dto.toDomain
import com.abanoub.cities.feature.cities.domain.model.City
import com.abanoub.cities.feature.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import java.io.IOException

/**
 * Implementation of [CityRepository] that provides city data with sorting and error handling.
 *
 * @param citiesDataSource The [CitiesDataSource] used to get and search cities.
 */
class CityRepositoryImpl(
    private val citiesDataSource: CitiesDataSource
) : CityRepository {
    override suspend fun searchCities(prefix: String): Flow<Result<List<City>, DataError>> =
        flow {
            try {
                val cities = citiesDataSource
                    .searchCities(prefix)
                    .map { it.toDomain() }
                    .sortedWith(compareBy<City> { it.name }.thenBy { it.country })
                emit(Result.Success(cities))
            } catch (e: IOException) {
                emit(Result.Error(DataError.Local.IO_EXCEPTION))
            } catch (e: SerializationException) {
                emit(Result.Error(DataError.Local.JSON_PARSING_ERROR))
            } catch (e: Exception) {
                emit(Result.Error(DataError.Local.UNKNOWN))
            }
        }
}