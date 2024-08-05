package com.abanoub.cities.feature.cities.data.di

import com.abanoub.cities.feature.cities.data.repository.CityRepositoryImpl
import com.abanoub.cities.feature.cities.domain.repository.CityRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CityRepository> { CityRepositoryImpl(get()) }
}