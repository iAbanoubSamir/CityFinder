package com.abanoub.cities.feature.cities.domain.di

import com.abanoub.cities.feature.cities.domain.usecase.SearchCitiesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { SearchCitiesUseCase(get()) }
}