package com.invatechs.data.di

import com.invatechs.data.network.retrofit.RetrofitBuilder
import com.invatechs.data.network.weather.WeatherApi
import com.invatechs.data.repository.WeatherRepositoryImpl
import com.invatechs.domain.contracts.WeatherRepository
import org.koin.dsl.module

object DataModule {

    val module = module {
        single<WeatherApi> { RetrofitBuilder.build().create(WeatherApi::class.java) }
        single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    }
}