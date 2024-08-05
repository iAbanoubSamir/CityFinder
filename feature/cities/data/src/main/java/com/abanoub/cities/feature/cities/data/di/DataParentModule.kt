package com.abanoub.cities.feature.cities.data.di

import org.koin.dsl.module

val citiesDataModule = module {
    includes(jsonModule, utilsModule, dataSourceModule, repositoryModule)
}