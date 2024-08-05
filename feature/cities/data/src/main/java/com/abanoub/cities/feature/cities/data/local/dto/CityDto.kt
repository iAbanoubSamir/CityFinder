package com.abanoub.cities.feature.cities.data.local.dto

import com.abanoub.cities.feature.cities.domain.model.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("country") var country: String,
    @SerialName("name") var name: String,
    @SerialName("_id") var id: Int,
    @SerialName("coord") var coordinates: Coordinates
)

@Serializable
data class Coordinates(
    @SerialName("lon") var lon: Double,
    @SerialName("lat") var lat: Double
)

fun CityDto.toDomain(): City =
    City(
        name = this.name,
        country = this.country,
        latitude = this.coordinates.lat,
        longitude = this.coordinates.lon
    )