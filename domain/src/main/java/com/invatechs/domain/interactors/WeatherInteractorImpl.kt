package com.invatechs.domain.interactors

import com.invatechs.domain.contracts.WeatherRepository
import com.invatechs.domain.models.Weather
import com.invatechs.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class WeatherInteractorImpl(
    private val repository: WeatherRepository
) : WeatherInteractor {

    override suspend fun getCurrentWeather(city: String?): Result<Weather> =
        repository.getCurrentWeather(city)

}