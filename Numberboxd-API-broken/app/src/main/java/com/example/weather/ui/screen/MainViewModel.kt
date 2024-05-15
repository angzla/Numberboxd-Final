package com.example.weather.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.TVresult
import com.example.weather.network.WeatherAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var weatherAPI: WeatherAPI
) : ViewModel() {

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Init)

    fun getWeather(apiKey: String, query: String, include_adult:String, language: String, page: String) {
        weatherUiState = WeatherUiState.Loading

        viewModelScope.launch {
            try {
//            starts the network communication and returns a weather object
                val result = weatherAPI.getWeather(apiKey, query, language, include_adult, page)

                weatherUiState = WeatherUiState.Success(result)

            } catch (e: Exception) {
                weatherUiState = WeatherUiState.Error(e.message!!)
            }
        }
    }

}

sealed interface WeatherUiState {
    object Init : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val tvResult: TVresult) : WeatherUiState
    data class Error(val errorMsg: String) : WeatherUiState
}