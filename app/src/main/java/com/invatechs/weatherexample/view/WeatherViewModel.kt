package com.invatechs.weatherexample.view

import androidx.lifecycle.viewModelScope
import com.invatechs.domain.interactors.WeatherInteractor
import com.invatechs.domain.utils.Result
import com.invatechs.weatherexample.view.base.BaseViewModel
import com.invatechs.weatherexample.view.state.WeatherViewAction
import com.invatechs.weatherexample.view.state.WeatherViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val DEFAULT_REQUEST_PARAMETER = "London"

class WeatherViewModel(
    private val weatherInterceptor: WeatherInteractor
) : BaseViewModel<WeatherViewState, WeatherViewAction>(
    initState = WeatherViewState()
) {

    init {
        getCurrentWeather(DEFAULT_REQUEST_PARAMETER)
    }

    fun onChangeCity(city: String?) {
//        reduceState { oldState -> oldState.copy(city = city).reduceButtonState() }
    }

    fun onSearchClick() {
        viewState.value?.city.let { value -> getCurrentWeather(value) }
//        reduceState { oldState -> oldState.copy(isLoading = true) }
    }

    private fun getCurrentWeather(city: String?) {
        viewModelScope.launch {
            val result = weatherInterceptor.getCurrentWeather(city)
            when (result) {
                is Result.Failure -> {

                }
                is Result.Success -> reduceState { oldState ->
                    oldState.copy(
                        weather = result.value,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun WeatherViewState.reduceButtonState() = copy(
        btnIsEnabled = !city.isNullOrEmpty()
    )
}