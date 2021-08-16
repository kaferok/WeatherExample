package com.invatechs.weatherexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.invatechs.domain.models.Weather
import com.invatechs.weatherexample.R
import com.invatechs.weatherexample.databinding.ActivityMainBinding
import com.invatechs.weatherexample.utils.animate
import com.invatechs.weatherexample.utils.launchWhenStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel by viewModel<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        observeViewModel()
        bindListeners()
    }

    private fun observeViewModel() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                viewState
                    .onEach { state ->
                        updateLoading(state?.isLoading == true)
                        updateWeatherData(state?.weather)
                    }
                    .collect()
            }
//            viewState
//                .onEach { state ->
//                    updateLoading(state?.isLoading == true)
//                    updateWeatherData(state?.weather)
//                }.launchWhenStarted(lifecycleScope)

//            actionState
//                .onEach { action ->
//
//                }.launchWhenStarted(lifecycleScope)
        }

    }

    private fun bindListeners() {
        with(binding!!) {
            etCity.doAfterTextChanged {
                viewModel.onChangeCity(etCity.text.toString())
            }
            btnSearch.setOnClickListener {
                viewModel.onSearchClick()
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        with(binding!!) {
            weatherGroup.isVisible = !isLoading
            loading.isVisible = isLoading
        }
    }

    private fun updateWeatherData(weather: Weather?) {
        with(binding!!) {
            root.animate {
                tvCity.text = weather?.name
                tvTemperature.text = StringBuilder()
                    .append(weather?.temperature)
                    .append(getString(R.string.temp_symbol))
                tvHumidity.text = getString(R.string.humidity_value, weather?.humidity)
                tvWindSpeed.text = getString(R.string.wind_value, weather?.windSpeed)
            }
        }
    }


}