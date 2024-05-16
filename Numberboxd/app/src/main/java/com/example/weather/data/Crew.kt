package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    @SerialName("adult")
    var adult: Boolean? = null,
    @SerialName("credit_id")
    var creditId: String? = null,
    @SerialName("department")
    var department: String? = null,
    @SerialName("gender")
    var gender: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("job")
    var job: String? = null,
    @SerialName("known_for_department")
    var knownForDepartment: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("original_name")
    var originalName: String? = null,
    @SerialName("popularity")
    var popularity: Double? = null,
    @SerialName("profile_path")
    var profilePath: String? = null
)