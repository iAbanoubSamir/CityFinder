package com.abanoub.cities.feature.cities.domain.di

import org.koin.dsl.module

val citiesDomainModule = module {
    includes(useCaseModule)
}