package com.invatechs.weatherexample.di

import com.invatechs.weatherexample.view.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    val module = module {
        viewModel { WeatherViewModel(get()) }
    }
}