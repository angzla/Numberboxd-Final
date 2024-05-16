package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("id")
    var id: Int = 0,
    @SerialName("name")
    var name: String = ""
)