package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("adult")
    var adult: Boolean = false,
    @SerialName("backdrop_path")
    var backdropPath: String = "",
    @SerialName("first_air_date")
    var firstAirDate: String = "",
    @SerialName("genre_ids")
    var genreIds: List<Int> = listOf(),
    @SerialName("id")
    var id: Int = 0,
    @SerialName("name")
    var name: String = "",
    @SerialName("origin_country")
    var originCountry: List<String> = listOf(),
    @SerialName("original_language")
    var originalLanguage: String = "",
    @SerialName("original_name")
    var originalName: String = "",
    @SerialName("overview")
    var overview: String = "",
    @SerialName("popularity")
    var popularity: Double = 0.0,
    @SerialName("poster_path")
    var posterPath: String = "",
    @SerialName("vote_average")
    var voteAverage: Double = 0.0,
    @SerialName("vote_count")
    var voteCount: Int = 0
)