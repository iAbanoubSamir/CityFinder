package com.abanoub.cities.feature.cities.data.local.datasource

import android.content.Context
import com.abanoub.cities.feature.cities.data.local.dto.CityDto
import com.abanoub.cities.feature.cities.data.util.parser.CityJsonParser
import com.abanoub.cities.feature.cities.data.util.strcture.Trie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * A class responsible for providing city data from a JSON file located in the assets.
 *
 * @param context The application context used to access the assets.
 * @param cityParser An instance of [CityJsonParser] for parsing JSON data.
 * @param trie An instance of [Trie] for efficient prefix-based searching.
 */
class LocalCitiesDataSource(
    private val context: Context,
    private val cityParser: CityJsonParser,
    private val trie: Trie
) : CitiesDataSource {

    private val citiesJsonPath = "cities.json"

    init {
        loadCitiesIntoTrie()
    }

    /**
     * Searches for cities matching the given prefix.
     * If the prefix is empty, returns all cities.
     *
     * @param prefix The prefix to search for city names.
     * @return A list of [CityDto] objects matching the prefix.
     */
    override suspend fun searchCities(prefix: String): List<CityDto> = withContext(Dispatchers.IO) {
        if (prefix.isEmpty()) emptyList() else trie.search(prefix)
    }

    /**
     * Loads and parses the JSON file containing city data.
     *
     * Reads the JSON file named "cities.json" from the assets folder and converts it into a list of [CityDto] objects.
     *
     * @return A list of [CityDto] objects parsed from the JSON file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private fun loadAndParseCities(): List<CityDto> {
        val jsonString = context.assets.open(citiesJsonPath).bufferedReader().use { it.readText() }
        return cityParser.parseCityData(jsonString)
    }

    /**
     * Loads cities from JSON and inserts them into the Trie.
     */
    private fun loadCitiesIntoTrie() {
        val cities = loadAndParseCities()
        cities.forEach { trie.insert(it) }
    }
}
