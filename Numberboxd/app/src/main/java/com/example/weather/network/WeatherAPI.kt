package com.example.weather.network

import com.example.weather.data.DetailsResult
import com.example.weather.data.EpisodeResult
import com.example.weather.data.TVresult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// WORKED
//https://api.themoviedb.org/
// PATH: 3/search/tv?
// query=Fleabag&api_key=89c5206ab9616493dff9a32d4351f46a

interface WeatherAPI {
    @GET("3/search/tv")
    suspend fun getWeather(
        @Query("api_key") api_key: String,
        @Query("query") city: String,
        @Query("include_adult") include_adult: String,
        @Query("language") language: String,
        @Query("page") page: String,
    ) : TVresult
}

// https://api.themoviedb.org/
// PATH: 3/tv/
// 67070? language=en-US&api_key=0ee8e741a484dbcebd23c922f5a8a778
interface DetailsFromID {
    @GET("3/tv/{series_id}?")
    suspend fun getDetails(
        @Path("series_id") series_id: String,
        @Query("language") language: String,
        @Query("api_key") api_key: String,
    ) : DetailsResult
}

//https://api.themoviedb.org/
// PATH: 3/tv/67070/season/1?
// &api_key=0ee8e741a484dbcebd23c922f5a8a778

interface SeasonFromID {
    @GET("3/tv/{series_id}/season/{season_number}?")
    suspend fun getEpisode(
        @Path("series_id") series_id: String,
        @Path("season_number") season_number: String,
        @Query("api_key") api_key: String,
    ) : EpisodeResult
}