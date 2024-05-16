package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TVresult(
    @SerialName("page")
    var page: Int = 0,
    @SerialName("results")
    var results: List<Result> = listOf(),
    @SerialName("total_pages")
    var totalPages: Int = 0,
    @SerialName("total_results")
    var totalResults: Int = 0
)