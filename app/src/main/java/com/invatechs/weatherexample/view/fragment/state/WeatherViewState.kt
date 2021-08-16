package com.invatechs.weatherexample.view.fragment.state

import com.invatechs.domain.models.Weather
import com.invatechs.weatherexample.view.base.ViewState

data class WeatherViewState(
    val weather: Weather? = null,
    val city: String? = null,
    val btnIsEnabled: Boolean = false,
    val isLoading: Boolean = true
) : ViewState