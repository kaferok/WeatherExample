package com.invatechs.data.network.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/find")
    suspend fun getWeather(
        @Query("q") city: String?,
        @Query("cnt") cnt: Int = 1
    ): Response<FindWeatherResponse>
}