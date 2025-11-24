package com.geox.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geox.app.data.*
import com.geox.app.data.mapper.*
import com.geox.app.di.AppModule
import com.geox.app.data.prefs.UserPreferences
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass
import com.geox.app.ui.screens.*
import com.geox.app.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.geox.app.utils.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

sealed class Screen {
    object Splash : Screen()
    object Home : Screen()
    object Insights : Screen()
    object Settings : Screen()
    object About : Screen()
}

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userPreferences = remember { AppModule.provideUserPreferences(this@MainActivity) }
            val isDarkModeEnabled = userPreferences.darkMode.collectAsState(initial = false).value

            GeoXTheme(darkTheme = isDarkModeEnabled) {
                // Initialize ViewModel
                val viewModel = remember {
                    HomeViewModel(
                        comCatRepository = AppModule.provideComCatRepository(this@MainActivity),
                        disasterRepository = AppModule.provideDisasterRepository(this@MainActivity)
                    )
                }
                
                // Refresh data on first launch
                LaunchedEffect(Unit) {
                    viewModel.refreshAll()
                }
                
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
                var showFilters by remember { mutableStateOf(false) }
                        var filters by remember {
                            mutableStateOf(
                                FilterState(
                                    minMagnitude = 2f,
                                    radius = 0, // Default to 0 (show all) - user can set radius if they want location filtering
                                    types = DisasterTypeFilters(
                                        volcano = true,
                                        wildfire = true,
                                        flood = true,
                                        storm = true,
                                        earthquake = true
                                    )
                                )
                            )
                        }
                var selectedDisaster by remember { mutableStateOf<DisasterData?>(null) }
                var userLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
                var hasLocationPermission by remember {
                    mutableStateOf(
                        LocationUtils.hasLocationPermission(this@MainActivity)
                    )
                }
                var refreshRate by remember { mutableStateOf("auto") }

                // Location permission launcher
                val locationPermissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
                    val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
                    hasLocationPermission = fineLocationGranted || coarseLocationGranted
                    
                    if (hasLocationPermission && userLocation == null) {
                        kotlinx.coroutines.CoroutineScope(Dispatchers.Main).launch {
                            val location = withContext(Dispatchers.IO) {
                                LocationUtils.getCurrentLocation(this@MainActivity)
                            }
                            location?.let {
                                userLocation = Pair(it.latitude, it.longitude)
                            }
                        }
                    }
                }

                // Request location permission if not granted
                LaunchedEffect(Unit) {
                    if (!hasLocationPermission) {
                        locationPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    } else if (hasLocationPermission && userLocation == null) {
                        // Get location if permission already granted
                        withContext(Dispatchers.IO) {
                            LocationUtils.getCurrentLocation(this@MainActivity)?.let { location ->
                                userLocation = Pair(location.latitude, location.longitude)
                            }
                        }
                    }
                }

                // Get real data from ViewModel
                val earthquakes = viewModel.earthquakes.collectAsState().value
                val disasters = viewModel.disasters.collectAsState().value
                
                // Combine and convert to DisasterData
                val allDisasterData = remember(earthquakes, disasters) {
                    (earthquakes.map { it.toDisasterData() } + disasters.map { it.toDisasterData() })
                        .sortedByDescending { 
                            when {
                                it.type == DisasterType.EARTHQUAKE -> {
                                    val eq = earthquakes.find { e -> e.id == it.id }
                                    eq?.time ?: 0L
                                }
                                else -> {
                                    val d = disasters.find { de -> de.id == it.id }
                                    d?.date ?: 0L
                                }
                            }
                        }
                }
                
                val filteredDisasters = remember(allDisasterData, filters, userLocation) {
                    val filtered = allDisasterData.filter { disaster ->
                        val typeFilter = filters.types.let {
                            when (disaster.type) {
                                DisasterType.EARTHQUAKE -> it.earthquake
                                DisasterType.WILDFIRE -> it.wildfire
                                DisasterType.VOLCANO -> it.volcano
                                DisasterType.FLOOD -> it.flood
                                DisasterType.STORM -> it.storm
                            }
                        }
                        val magnitudeFilter = disaster.magnitude == null || disaster.magnitude >= filters.minMagnitude
                        
                        // Location filter: only apply if we have location AND radius > 0
                        // If radius is 0 or negative, show all disasters regardless of location
                        val locationFilter = if (userLocation != null && filters.radius > 0) {
                            try {
                                val distance = LocationUtils.calculateDistance(
                                    userLocation!!.first, // latitude
                                    userLocation!!.second, // longitude
                                    disaster.coordinates.first, // latitude
                                    disaster.coordinates.second // longitude
                                )
                                distance <= filters.radius
                            } catch (e: Exception) {
                                true // If distance calculation fails, show it
                            }
                        } else {
                            true // Show all if no location or radius is 0/negative
                        }
                        
                        typeFilter && magnitudeFilter && locationFilter
                    }
                    
                    // If no disasters found and we have location, show the nearest one
                    if (filtered.isEmpty() && userLocation != null && filters.radius > 0) {
                        val nearest = allDisasterData
                            .filter { disaster ->
                                val typeFilter = filters.types.let {
                                    when (disaster.type) {
                                        DisasterType.EARTHQUAKE -> it.earthquake
                                        DisasterType.WILDFIRE -> it.wildfire
                                        DisasterType.VOLCANO -> it.volcano
                                        DisasterType.FLOOD -> it.flood
                                        DisasterType.STORM -> it.storm
                                    }
                                }
                                val magnitudeFilter = disaster.magnitude == null || disaster.magnitude >= filters.minMagnitude
                                typeFilter && magnitudeFilter
                            }
                            .minByOrNull { disaster ->
                                try {
                                    LocationUtils.calculateDistance(
                                        userLocation!!.first,
                                        userLocation!!.second,
                                        disaster.coordinates.first,
                                        disaster.coordinates.second
                                    )
                                } catch (e: Exception) {
                                    Double.MAX_VALUE
                                }
                            }
                        if (nearest != null) listOf(nearest) else emptyList()
                    } else {
                        filtered
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Background animated orbs
                        AnimatedBackgroundOrbs()

                        when (currentScreen) {
                            is Screen.Splash -> {
                                SplashScreen(
                                    onGetStarted = { currentScreen = Screen.Home }
                                )
                            }
                            is Screen.Home -> {
                                HomeScreen(
                                    disasters = filteredDisasters,
                                    onFilterClick = { showFilters = true },
                                    onDisasterClick = { selectedDisaster = it },
                                    userLocation = userLocation
                                )
                            }
                            is Screen.Insights -> {
                                InsightsScreen(viewModel = viewModel)
                            }
                            is Screen.Settings -> {
                                SettingsScreen(
                                    viewModel = viewModel,
                                    userPreferences = userPreferences,
                                    onRefreshRateChange = { rate ->
                                        refreshRate = rate
                                        // Note: Setting display refresh rate requires API 31+ and system-level permissions
                                        // For now, we store the preference. The actual implementation would require
                                        // DisplayManager API on Android 11+ or WindowManager.setPreferredDisplayModeId on API 31+
                                    }
                                )
                            }
                            is Screen.About -> {
                                AboutScreen()
                            }
                        }

                        // Bottom Navigation (hide on splash)
                        if (currentScreen !is Screen.Splash) {
                            BottomNavigation(
                                currentScreen = currentScreen,
                                onScreenChange = { currentScreen = it },
                                onFiltersClick = { showFilters = true },
                                showFilters = showFilters,
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }

                    // Filters sheet
                    if (showFilters) {
                        FiltersScreen(
                            onClose = { showFilters = false },
                            onApply = { newFilters ->
                                filters = newFilters
                                showFilters = false
                            },
                            initialFilters = filters
                        )
                    }

                    // Alert detail modal
                    selectedDisaster?.let { disaster ->
                        AlertDetailModal(
                            disaster = disaster,
                            onDismiss = { selectedDisaster = null },
                            onSourceClick = { },
                            onSaveClick = { },
                            onShareClick = { }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(
    currentScreen: Screen,
    onScreenChange: (Screen) -> Unit,
    onFiltersClick: () -> Unit,
    showFilters: Boolean,
    modifier: Modifier = Modifier
) {
    LiquidGlass(
        modifier = modifier.fillMaxWidth(),
        cornerRadius = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavButton(
                icon = Icons.Default.Home,
                label = "Home",
                active = currentScreen is Screen.Home,
                onClick = { onScreenChange(Screen.Home) }
            )
            NavButton(
                icon = Icons.Default.BarChart,
                label = "Insights",
                active = currentScreen is Screen.Insights,
                onClick = { onScreenChange(Screen.Insights) }
            )
            NavButton(
                icon = Icons.Default.Search,
                label = "Filters",
                active = showFilters,
                onClick = onFiltersClick
            )
            NavButton(
                icon = Icons.Default.Settings,
                label = "Settings",
                active = currentScreen is Screen.Settings,
                onClick = { onScreenChange(Screen.Settings) }
            )
            NavButton(
                icon = Icons.Default.Info,
                label = "About",
                active = currentScreen is Screen.About,
                onClick = { onScreenChange(Screen.About) }
            )
        }
    }
}

@Composable
fun NavButton(
    icon: ImageVector,
    label: String,
    active: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (active) 1f else 0.95f,
        label = "scale"
    )

    Column(
        modifier = Modifier
            .scale(scale)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (active) LimeGreen.copy(alpha = 0.1f) else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (active) LimeGreen else TextTertiary,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = if (active) TextPrimary else TextTertiary
        )
    }
}

@Composable
fun AnimatedBackgroundOrbs() {
    val infiniteTransition = rememberInfiniteTransition(label = "orbs")

    val orb1X by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb1X"
    )

    val orb1Y by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb1Y"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Orb 1
        Box(
            modifier = Modifier
                .offset(
                    x = (orb1X * 150 - 75).dp,
                    y = (orb1Y * 100 - 50).dp
                )
                .size(600.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            LimeGreen.copy(alpha = 0.25f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Orb 2
        val orb2X by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(25000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "orb2X"
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(
                    x = (orb2X * 80 - 40).dp,
                    y = (orb2X * 80 - 40).dp
                )
                .size(500.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            FloodBlue.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Orb 3
        val orb3X by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(22000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "orb3X"
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = (orb3X * 60 - 30).dp,
                    y = (orb3X * 100 - 50).dp
                )
                .size(400.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            VolcanoPurple.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
    }
}

