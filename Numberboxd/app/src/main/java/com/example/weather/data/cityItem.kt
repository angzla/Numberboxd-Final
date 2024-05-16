package com.example.weather.data

data class cityItem(
    val city: String,
    val showRating:String,
    var showcategory:showCategory,
    var isFavorite: Boolean,
    var ID: String,
    var posterPath: String

) : java.io.Serializable

enum class showCategory {
    Currently_Watching, Watchlist;
}
