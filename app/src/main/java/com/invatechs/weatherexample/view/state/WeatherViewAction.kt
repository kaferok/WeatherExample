package com.invatechs.weatherexample.view.state

import com.invatechs.weatherexample.view.base.ViewAction

sealed class WeatherViewAction : ViewAction {

    object Error : WeatherViewAction()

    data class RequestMade(val city: String?) : WeatherViewAction()
}