package com.abanoub.cities.feature.cities.data.di

import com.abanoub.cities.feature.cities.data.local.datasource.CitiesDataSource
import com.abanoub.cities.feature.cities.data.local.datasource.LocalCitiesDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<CitiesDataSource> { LocalCitiesDataSource(get(), get(), get()) }
}