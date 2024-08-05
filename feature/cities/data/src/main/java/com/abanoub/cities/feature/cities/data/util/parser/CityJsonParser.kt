package com.abanoub.cities.feature.cities.data.util.parser

import com.abanoub.cities.feature.cities.data.local.dto.CityDto
import kotlinx.serialization.json.Json

/**
 * A class responsible for parsing city data from a JSON string.
 *
 * @param json An instance of [Json] for handling JSON serialization and deserialization.
 */
class CityJsonParser(private val json: Json) {

    /**
     * Parses a JSON string containing city data into a list of [CityDto] objects.
     *
     * @param jsonString A JSON-formatted string representing city data.
     * @return A list of [CityDto] objects parsed from the JSON string.
     * @throws [kotlinx.serialization.SerializationException] If an error occurs during JSON deserialization.
     */
    fun parseCityData(jsonString: String): List<CityDto> {
        return json.decodeFromString(jsonString)
    }
}