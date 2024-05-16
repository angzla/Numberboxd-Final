package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("air_date")
    var airDate: String = "",
    @SerialName("episode_count")
    var episodeCount: Int = 0,
    @SerialName("id")
    var id: Int = 0,
    @SerialName("name")
    var name: String = "",
    @SerialName("overview")
    var overview: String = "",
    @SerialName("poster_path")
    var posterPath: String = "",
    @SerialName("season_number")
    var seasonNumber: Int = 0,
    @SerialName("vote_average")
    var voteAverage: Double = 0.0
)