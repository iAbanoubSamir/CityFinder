package com.abanoub.cities.feature.cities.data.util.strcture

import com.abanoub.cities.feature.cities.data.local.dto.CityDto

/**
 * Represents a node in a [Trie] data structure.
 *
 * Each node in the Trie corresponds to a single character and can have multiple child nodes.
 * It also tracks whether the node marks the end of a valid key and can optionally store
 * additional information, such as a [CityDto] object in this case.
 */
class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var isEndOfWord: Boolean = false
    var city: CityDto? = null
}