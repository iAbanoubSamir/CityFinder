package com.abanoub.cities.feature.cities.data.di

import com.abanoub.cities.feature.cities.data.util.strcture.Trie
import org.koin.dsl.module

val utilsModule = module {
    single { Trie() }
}