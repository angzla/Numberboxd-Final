package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedBy(
    @SerialName("credit_id")
    var creditId: String = "",
    @SerialName("gender")
    var gender: Int = 0,
    @SerialName("id")
    var id: Int = 0,
    @SerialName("name")
    var name: String = "",
    @SerialName("original_name")
    var originalName: String = "",
    @SerialName("profile_path")
    var profilePath: String = ""
)