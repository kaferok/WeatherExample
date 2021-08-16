package com.invatechs.domain.interactors

import com.invatechs.domain.models.Weather
import com.invatechs.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherInteractor {

    suspend fun getCurrentWeather(city: String?): Result<Weather>
}