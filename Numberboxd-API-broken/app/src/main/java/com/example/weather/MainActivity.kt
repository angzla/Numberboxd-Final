package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.ui.navigation.MainNavigation
import com.example.weather.ui.screen.CityScreen
import com.example.weather.ui.screen.MainScreen
import com.example.weather.ui.theme.HttpRetrofitDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpRetrofitDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherNavHost()
                }
            }
        }
    }
}


@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainNavigation.CityScreen.route
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {

        composable(MainNavigation.CityScreen.route) {
            CityScreen(
                onNavigateToMain = { cityLocation ->
                navController.navigate(
                    MainNavigation.MainScreen.createRoute(cityLocation))
            }
            )
        }

        composable(MainNavigation.MainScreen.route){
            backStackEntry ->
            val cityLocation = backStackEntry.arguments?.getString("cityLocation")
            cityLocation?.let {
                MainScreen(cityLocation = it)
            }
        }
    }
}