package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Network(
    @SerialName("id")
    var id: Int = 0,
    @SerialName("logo_path")
    var logoPath: String = "",
    @SerialName("name")
    var name: String = "",
    @SerialName("origin_country")
    var originCountry: String = ""
)