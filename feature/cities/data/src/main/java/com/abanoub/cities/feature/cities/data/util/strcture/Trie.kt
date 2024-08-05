package com.abanoub.cities.feature.cities.data.util.strcture

import com.abanoub.cities.feature.cities.data.local.dto.CityDto

/**
 * A Trie data structure is used for efficient prefix-based searching.
 *
 * **Why Trie?**
 *
 * 1. **Prefix Matching Efficiency**: Trie allows for quick prefix searches, which is ideal for autocomplete or search-as-you-type features. This is because Trie nodes represent individual characters, making it easy to traverse based on the prefix input.
 *
 * 2. **Performance**: Trie provides better performance for searching operations compared to linear search algorithms or even binary search trees when dealing with a large dataset of strings. It supports O(m) time complexity for search operations, where m is the length of the prefix.
 *
 * 3. **Scalability**: With the Trie structure, as the dataset grows, the time complexity for searches remains manageable, which is crucial for handling large datasets like our cities list.
 *
 * 4. **Efficient Memory Use**: Although Tries use more memory than simple hash tables or arrays, they provide efficient prefix querying which aligns with our use case of searching cities by prefix.
 *
 * This makes Trie a suitable choice for our city search feature, where users can type partial city names to get matching results quickly.
 *
 * This Trie implementation supports inserting city names and searching for cities
 * based on a given prefix. Each node in the Trie represents a character in city names,
 * and the Trie can efficiently find all cities that start with a specific prefix.
 */
class Trie {

    private val root = TrieNode()

    /**
     * Inserts a city into the Trie.
     *
     * This method adds a city to the Trie, creating nodes for each character in the city's name.
     * The `city` parameter is stored at the end of the city name.
     *
     * @param city The city to be inserted into the Trie. The city's name is used as the key.
     */
    fun insert(city: CityDto) {
        var currentNode = root
        val cityName = city.name.lowercase()
        for (char in cityName) {
            currentNode = currentNode.children.getOrPut(char) { TrieNode() }
        }
        currentNode.isEndOfWord = true
        currentNode.city = city
    }

    /**
     * Searches for cities with the given prefix.
     *
     * This method finds all cities that have names starting with the specified prefix.
     * It traverses the Trie based on the prefix and collects cities from the nodes where
     * the prefix ends.
     *
     * @param prefix The prefix to search for. The search is case-insensitive.
     * @return A list of [CityDto] objects representing cities that start with the given prefix.
     */
    fun search(prefix: String): List<CityDto> {
        var currentNode = root
        val lowerPrefix = prefix.lowercase()
        for (char in lowerPrefix) {
            currentNode = currentNode.children[char] ?: return emptyList()
        }
        return findCitiesFromNode(currentNode)
    }

    /**
     * Helper method to find all cities starting from a given Trie node.
     *
     * This method recursively collects all cities from the given node. It traverses all child nodes
     * to gather cities whose names start with the prefix associated with the given node.
     *
     * @param node The starting node for the search.
     * @return A list of [CityDto] objects representing cities found from the given node.
     */
    private fun findCitiesFromNode(node: TrieNode): List<CityDto> {
        val cities = mutableListOf<CityDto>()
        if (node.isEndOfWord && node.city != null) {
            cities.add(node.city!!)
        }
        for (child in node.children.values) {
            cities.addAll(findCitiesFromNode(child))
        }
        return cities
    }
}