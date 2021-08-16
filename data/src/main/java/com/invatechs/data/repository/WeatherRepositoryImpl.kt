package com.invatechs.data.repository

import com.invatechs.data.network.bodyOrFailure
import com.invatechs.data.network.weather.WeatherApi
import com.invatechs.data.network.weather.WeatherResponse
import com.invatechs.data.network.weather.toDomainModel
import com.invatechs.domain.contracts.WeatherRepository
import com.invatechs.domain.models.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.invatechs.domain.utils.Result
import kotlinx.coroutines.Dispatchers

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String?): Result<Weather> {
        val result = api.getWeather(city).bodyOrFailure()
        return when (result) {
            is Result.Failure -> Result.Failure(result.error)
            is Result.Success -> Result.Success(
                result.value.list.map(WeatherResponse::toDomainModel).first())
        }
    }

}