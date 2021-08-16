package com.invatechs.weatherexample.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.invatechs.domain.models.Weather
import com.invatechs.weatherexample.R
import com.invatechs.weatherexample.databinding.FragmentWeatherBinding
import com.invatechs.weatherexample.utils.animate
import com.invatechs.weatherexample.utils.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StringBuilder

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        bindListeners()
    }
    private fun observeViewModel() {
        with(viewModel) {
            viewState
                .onEach { state ->
                    updateLoading(state?.isLoading == true)
                    updateWeatherData(state?.weather)
                }.launchWhenStarted(lifecycleScope)

//            actionState
//                .onEach { action ->
//
//                }.launchWhenStarted(lifecycleScope)
        }
    }

    private fun bindListeners() {
        with(binding) {
            etCity.doAfterTextChanged {
                viewModel.onChangeCity(etCity.text.toString())
            }
            btnSearch.setOnClickListener {
                viewModel.onSearchClick()
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        with(binding) {
            loading.isVisible = isLoading
        }
    }

    private fun updateWeatherData(weather: Weather?) {
        with(binding) {
            root.animate {
                tvCity.text = weather?.name
                tvTemperature.text = StringBuilder()
                    .append(weather?.temperature)
                    .append(getString(R.string.temp_symbol))
                tvHumidity.text = getString(
                    R.string.humidity_value,
                    weather?.humidity
                )
                tvWindSpeed.text =
                    getString(R.string.wind_value, weather?.windSpeed)
            }
        }
    }


}