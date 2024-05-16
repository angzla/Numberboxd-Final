package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastEpisodeToAir(
    @SerialName("air_date")
    var airDate: String = "",
    @SerialName("episode_number")
    var episodeNumber: Int = 0,
    @SerialName("episode_type")
    var episodeType: String = "",
    @SerialName("id")
    var id: Int = 0,
    @SerialName("name")
    var name: String = "",
    @SerialName("overview")
    var overview: String = "",
    @SerialName("production_code")
    var productionCode: String = "",
    @SerialName("runtime")
    var runtime: Int = 0,
    @SerialName("season_number")
    var seasonNumber: Int = 0,
    @SerialName("show_id")
    var showId: Int = 0,
    @SerialName("still_path")
    var stillPath: String = "",
    @SerialName("vote_average")
    var voteAverage: Double = 0.0,
    @SerialName("vote_count")
    var voteCount: Int = 0
)