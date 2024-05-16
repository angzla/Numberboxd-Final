package com.example.weather.ui.navigation

sealed class MainNavigation(val route: String) {
    object CityScreen : MainNavigation("cityscreen")
    object MainScreen : MainNavigation("mainscreen?seriesId={seriesId}") {
        fun createRoute(seriesId: String) : String {
            return "mainscreen?seriesId=$seriesId"
        }
    }
}