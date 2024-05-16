package com.example.weather.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.DetailsResult
import com.example.weather.data.EpisodeResult
import com.example.weather.data.Season
import com.example.weather.data.TVresult
import com.example.weather.network.DetailsFromID
import com.example.weather.network.SeasonFromID
import com.example.weather.network.WeatherAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var weatherAPI: WeatherAPI,
    var detailsFromID: DetailsFromID,
    var seasonFromID: SeasonFromID
) : ViewModel() {

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Init)
    var detailsUiState: DetailsUiState by mutableStateOf(DetailsUiState.Init)
    var seasonsUiState: SeasonsUiState by mutableStateOf(SeasonsUiState.Init)

    fun getWeather(apiKey: String, query: String, include_adult:String, language: String, page: String) {
        weatherUiState = WeatherUiState.Loading

        viewModelScope.launch {
            try {
//            starts the network communication and returns a weather object
                val resultSearch = weatherAPI.getWeather(apiKey, query, language, include_adult, page)

                weatherUiState = WeatherUiState.Success(resultSearch)

            } catch (e: Exception) {
                weatherUiState = WeatherUiState.Error(e.message!!)
            }
        }
    }

    fun getDetails(series_id: String, language: String, apiKey: String ){

        detailsUiState = DetailsUiState.Loading

        viewModelScope.launch {
            try {
//            starts the network communication and returns a weather object
                val resultDetails = detailsFromID.getDetails(series_id, language, apiKey)

                detailsUiState = DetailsUiState.Success(resultDetails)

            } catch (e: Exception) {
                detailsUiState = DetailsUiState.Error(e.message!!)
            }
        }
    }

    fun getEpisodes(series_id: String, season_number: String, apiKey: String ){

        seasonsUiState = SeasonsUiState.Loading

        viewModelScope.launch {
            try {
//            starts the network communication and returns a weather object
                val seasonDetails = seasonFromID.getEpisode(series_id, season_number, apiKey)

                seasonsUiState = SeasonsUiState.Success(seasonDetails)

            } catch (e: Exception) {
                seasonsUiState = SeasonsUiState.Error(e.message!!)
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

sealed interface DetailsUiState {
    object Init : DetailsUiState
    object Loading : DetailsUiState
    data class Success(val detailsResult: DetailsResult) : DetailsUiState
    data class Error(val errorMsg: String) : DetailsUiState
}

sealed interface SeasonsUiState {
    object Init : SeasonsUiState
    object Loading : SeasonsUiState
    data class Success(val episodeResult: EpisodeResult) : SeasonsUiState
    data class Error(val errorMsg: String) : SeasonsUiState
}