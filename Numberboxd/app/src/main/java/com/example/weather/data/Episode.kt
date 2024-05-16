package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    @SerialName("air_date")
    var airDate: String? = null,
    @SerialName("crew")
    var crew: List<Crew?>? = null,
    @SerialName("episode_number")
    var episodeNumber: Int? = null,
    @SerialName("episode_type")
    var episodeType: String? = null,
    @SerialName("guest_stars")
    var guestStars: List<GuestStar?>? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("overview")
    var overview: String? = null,
    @SerialName("production_code")
    var productionCode: String? = null,
    @SerialName("runtime")
    var runtime: Int? = null,
    @SerialName("season_number")
    var seasonNumber: Int? = null,
    @SerialName("show_id")
    var showId: Int? = null,
    @SerialName("still_path")
    var stillPath: String? = null,
    @SerialName("vote_average")
    var voteAverage: Double? = null,
    @SerialName("vote_count")
    var voteCount: Int? = null
)