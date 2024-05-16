package com.example.weather.data


import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.ArrayList

@Serializable
data class DetailsResult(
    @SerialName("adult")
    var adult: Boolean = false,
    @SerialName("backdrop_path")
    var backdropPath: String? = "",
    @SerialName("created_by")
    var createdBy: List<CreatedBy> = listOf(),
    @SerialName("episode_run_time")
    var episodeRunTime: List<Int> = listOf(),
    @SerialName("first_air_date")
    var firstAirDate: String? = "",
    @SerialName("genres")
    var genres: List<Genre> = listOf(),
    @SerialName("homepage")
    var homepage: String? = "",
    @SerialName("id")
    var id: Int = 0,
    @SerialName("in_production")
    var inProduction: Boolean = false,
    @SerialName("languages")
    var languages: ArrayList<String>? = ArrayList(),
    @SerialName("last_air_date")
    var lastAirDate: String? = "",
    @SerialName("last_episode_to_air")
    var lastEpisodeToAir: LastEpisodeToAir = LastEpisodeToAir(),
    @SerialName("name")
    var name: String? = "",
    @SerialName("networks")
    var networks: List<Network> = listOf(),
    @SerialName("next_episode_to_air")
    var nextEpisodeToAir: NextEpisodeToAir? = NextEpisodeToAir(),
    @SerialName("number_of_episodes")
    var numberOfEpisodes: Int = 0,
    @SerialName("number_of_seasons")
    var numberOfSeasons: Int = 0,
    @SerialName("origin_country")
    var originCountry: ArrayList<String>? = ArrayList(),
    @SerialName("original_language")
    var originalLanguage: String? = "",
    @SerialName("original_name")
    var originalName: String? = "",
    @SerialName("overview")
    var overview: String? = "",
    @SerialName("popularity")
    var popularity: Double = 0.0,
    @SerialName("poster_path")
    var posterPath: String? = "",
    @SerialName("production_companies")
    var productionCompanies: List<ProductionCompany> = listOf(),
    @SerialName("production_countries")
    var productionCountries: List<ProductionCountry> = listOf(),
    @SerialName("seasons")
    var seasons: List<Season> = listOf(),
    @SerialName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage> = listOf(),
    @SerialName("status")
    var status: String? = "",
    @SerialName("tagline")
    var tagline: String? = "",
    @SerialName("type")
    var type: String? = "",
    @SerialName("vote_average")
    var voteAverage: Double = 0.0,
    @SerialName("vote_count")
    var voteCount: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        TODO("createdBy"),
        TODO("episodeRunTime"),
        parcel.readString(),
        TODO("genres"),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList(),
        parcel.readString(),
        TODO("lastEpisodeToAir"),
        parcel.readString(),
        TODO("networks"),
        TODO("nextEpisodeToAir"),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        TODO("productionCompanies"),
        TODO("productionCountries"),
        TODO("seasons"),
        TODO("spokenLanguages"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdropPath)
        parcel.writeString(firstAirDate)
        parcel.writeString(homepage)
        parcel.writeInt(id)
        parcel.writeByte(if (inProduction) 1 else 0)
        parcel.writeStringList(languages)
        parcel.writeString(lastAirDate)
        parcel.writeString(name)
        parcel.writeInt(numberOfEpisodes)
        parcel.writeInt(numberOfSeasons)
        parcel.writeStringList(originCountry)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalName)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(status)
        parcel.writeString(tagline)
        parcel.writeString(type)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailsResult> {
        override fun createFromParcel(parcel: Parcel): DetailsResult {
            return DetailsResult(parcel)
        }

        override fun newArray(size: Int): Array<DetailsResult?> {
            return arrayOfNulls(size)
        }
    }
}