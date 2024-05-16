package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResult(
    @SerialName("air_date")
    var airDate: String? = null,
    @SerialName("episodes")
    var episodes: List<Episode?>? = null,
    @SerialName("_id")
    var mongoid: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("overview")
    var overview: String? = null,
    @SerialName("poster_path")
    var posterPath: String? = null,
    @SerialName("season_number")
    var seasonNumber: Int? = null,
    @SerialName("vote_average")
    var voteAverage: Double? = null
)