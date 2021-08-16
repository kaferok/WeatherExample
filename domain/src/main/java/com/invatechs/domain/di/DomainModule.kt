package com.invatechs.domain.di

import com.invatechs.domain.interactors.WeatherInteractor
import com.invatechs.domain.interactors.WeatherInteractorImpl
import org.koin.dsl.module

object DomainModule {

    val module = module {
        single<WeatherInteractor> { WeatherInteractorImpl(get()) }
    }
}