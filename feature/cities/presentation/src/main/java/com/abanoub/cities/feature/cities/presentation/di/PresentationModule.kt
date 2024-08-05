package com.abanoub.cities.feature.cities.presentation.di

import com.abanoub.cities.feature.cities.presentation.CitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val citiesPresentationModule = module {
    viewModel { CitiesViewModel(get()) }
}