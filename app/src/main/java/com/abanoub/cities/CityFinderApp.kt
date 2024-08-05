package com.abanoub.cities

import android.app.Application
import com.abanoub.cities.feature.cities.data.di.citiesDataModule
import com.abanoub.cities.feature.cities.domain.di.citiesDomainModule
import com.abanoub.cities.feature.cities.presentation.di.citiesPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CityFinderApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CityFinderApp)
            modules(citiesDataModule, citiesDomainModule, citiesPresentationModule)
        }

    }
}