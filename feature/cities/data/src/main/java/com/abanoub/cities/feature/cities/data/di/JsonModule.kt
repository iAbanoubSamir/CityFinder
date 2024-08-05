package com.abanoub.cities.feature.cities.data.di

import com.abanoub.cities.feature.cities.data.util.parser.CityJsonParser
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val jsonModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single { CityJsonParser(get()) }
}