package com.example.weather.network

import com.example.weather.data.TVresult
import retrofit2.http.GET
import retrofit2.http.Query

//
// HOST: https://api.nasa.gov/
// PATH: mars-photos/api/v1/rovers/curiosity/photos
// QUERY PARAMS:
// ?
// earth_date=2024-1-4
// &
// api_key=DEMO_KEY

//HOST: https://api.openweathermap.org/
// PATH: data/2.5/weather
// QUERY PARAMS:
// ?
// q=Budapest,hu
// &
// units=metric
// &
// appid=808ec91591e0ff979a1e618ee2462adb

//https://api.themoviedb.org/
// PATH: 3/search/tv?
// query=Stranger%20Things&include_adult=false&language=en-US&page=1

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