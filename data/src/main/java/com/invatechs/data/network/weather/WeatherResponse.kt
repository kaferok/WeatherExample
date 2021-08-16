package com.invatechs.data.network.weather

import com.google.gson.annotations.SerializedName
import com.invatechs.domain.models.Weather

data class FindWeatherResponse(
    val message: String?,
    val cod: String?,
    val count: Int?,
    val list: List<WeatherResponse>
)

data class WeatherResponse(
    val id: Int,
    val coord: CoordModel?,
    val weather: WeatherModel?,
    @SerializedName("main")
    val temperature: TemperatureModel?,
    val wind: WindModel?,
    val name: String?
)

data class CoordModel(
    val lon: Double?,
    val lat: Double?
)

data class WeatherModel(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?,
)

data class TemperatureModel(
    val temp: Double?,
    val pressure: Int?,
    val humidity: Int?,
    @SerializedName("temp_min")
    val tempmin: Double?,
    @SerializedName("temp_max")
    val tempmax: Double?
)

data class WindModel(
    val speed: Double?
)

fun WeatherResponse.toDomainModel() = Weather(
    id = id,
    temperature = (temperature?.temp ?: 0 - 273).toString(),
    humidity = temperature?.humidity.toString(),
    windSpeed = wind?.speed.toString(),
    name = name
)