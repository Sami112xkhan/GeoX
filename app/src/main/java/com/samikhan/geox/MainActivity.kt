package com.samikhan.geox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samikhan.geox.di.AppModule
import com.samikhan.geox.ui.screens.AlertDetailScreen
import com.samikhan.geox.ui.screens.HomeScreen
import com.samikhan.geox.ui.screens.InsightsScreen
import com.samikhan.geox.ui.screens.SettingsScreen
import com.samikhan.geox.ui.screens.SplashScreen
import com.samikhan.geox.ui.theme.GeoXTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var useDarkTheme by remember { mutableStateOf(false) }
            GeoXTheme(darkTheme = useDarkTheme) {
                Surface {
                    val navController = rememberNavController()
                    GeoXNavHost(
                        navController = navController,
                        onToggleDark = { useDarkTheme = !useDarkTheme }
                    )
                }
            }
        }
    }
}

object Routes {
    const val Splash = "splash"
    const val Home = "home"
    const val AlertDetail = "alert_detail/{eventType}/{eventId}"
    const val Insights = "insights"
    const val Settings = "settings"
}

@Composable
private fun GeoXNavHost(
    navController: NavHostController,
    onToggleDark: () -> Unit,
) {
    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable(Routes.Splash) {
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            }
            SplashScreen()
        }
        composable(Routes.Home) {
            HomeScreen(
                onOpenAlert = { event ->
                    val eventType = when (event) {
                        is com.samikhan.geox.data.model.EarthquakeEvent -> "earthquake"
                        is com.samikhan.geox.data.model.DisasterEvent -> "disaster"
                        else -> "unknown"
                    }
                    val eventId = when (event) {
                        is com.samikhan.geox.data.model.EarthquakeEvent -> event.id
                        is com.samikhan.geox.data.model.DisasterEvent -> event.id
                        else -> "unknown"
                    }
                    navController.navigate("alert_detail/$eventType/$eventId")
                },
                onOpenInsights = { navController.navigate(Routes.Insights) },
                onOpenSettings = { navController.navigate(Routes.Settings) }
            )
        }
        composable("alert_detail/{eventType}/{eventId}") { backStackEntry ->
            val eventType = backStackEntry.arguments?.getString("eventType") ?: "unknown"
            val eventId = backStackEntry.arguments?.getString("eventId") ?: "unknown"
            
            // For now, create a simple event object - in a real app, you'd fetch from repository
            val event = when (eventType) {
                "earthquake" -> com.samikhan.geox.data.model.EarthquakeEvent(
                    id = eventId,
                    time = System.currentTimeMillis(),
                    latitude = 37.7749,
                    longitude = -122.4194,
                    magnitude = 4.2,
                    depth = 10.5,
                    place = "San Francisco, CA",
                    url = "https://earthquake.usgs.gov/earthquakes/eventpage/$eventId"
                )
                "disaster" -> com.samikhan.geox.data.model.DisasterEvent(
                    id = eventId,
                    title = "Sample Disaster",
                    description = "Sample disaster description",
                    category = "wildfires",
                    latitude = 37.7749,
                    longitude = -122.4194,
                    date = System.currentTimeMillis(),
                    url = "https://eonet.gsfc.nasa.gov/api/v3/events/$eventId"
                )
                else -> null
            }
            
            if (event != null) {
                AlertDetailScreen(
                    event = event,
                    onBack = { navController.popBackStack() }
                )
            }
        }
        composable(Routes.Insights) {
            InsightsScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.Settings) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onToggleDark = onToggleDark
            )
        }
    }
}


