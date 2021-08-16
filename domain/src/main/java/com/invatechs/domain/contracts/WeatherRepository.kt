package com.invatechs.domain.contracts

import com.invatechs.domain.models.Weather
import com.invatechs.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(city: String?): Result<Weather>
}