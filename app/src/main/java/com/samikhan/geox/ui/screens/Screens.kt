package com.samikhan.geox.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samikhan.geox.data.repository.EarthquakeRepository
import com.samikhan.geox.data.repository.DisasterRepository
import com.samikhan.geox.data.model.EarthquakeEvent
import com.samikhan.geox.data.model.DisasterEvent
import com.samikhan.geox.viewmodel.HomeViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.samikhan.geox.ui.components.DisasterMap
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.samikhan.geox.ui.components.FrostedChartCard

@Composable
fun SplashScreen() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
        label = "splash"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .alpha(alpha)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent)
                        ),
                        shape = MaterialTheme.shapes.large
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "GeoX",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.alpha(alpha)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenAlert: (Any) -> Unit,
    onOpenInsights: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    val context = LocalContext.current
    val vm: HomeViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val earthquakeRepo = com.samikhan.geox.di.AppModule.provideEarthquakeRepository(context.applicationContext)
            val disasterRepo = com.samikhan.geox.di.AppModule.provideDisasterRepository(context.applicationContext)
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(earthquakeRepo, disasterRepo) as T
        }
    })
    LaunchedEffect(Unit) { 
        vm.refreshEarthquakes()
        vm.refreshDisasters()
    }
    val earthquakes by vm.earthquakes.collectAsState()
    val disasters by vm.disasters.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("GeoX") },
                actions = {
                    IconButton(onClick = onOpenInsights) { Icon(Icons.Default.Notifications, contentDescription = null) }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onOpenSettings) { Icon(Icons.Default.Settings, contentDescription = null) }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                DisasterMap(
                    earthquakes = earthquakes,
                    disasters = disasters,
                    modifier = Modifier.fillMaxSize(),
                    onEventSelected = onOpenAlert
                )
            }
            Spacer(Modifier.height(16.dp))
            Text("Latest Alerts", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                // Show earthquakes
                items(earthquakes.take(5)) { earthquake ->
                    EarthquakeCard(
                        earthquake = earthquake,
                        onClick = { onOpenAlert(earthquake) }
                    )
                }
                // Show disasters
                items(disasters.take(5)) { disaster ->
                    DisasterCard(
                        disaster = disaster,
                        onClick = { onOpenAlert(disaster) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EarthquakeCard(earthquake: EarthquakeEvent, onClick: () -> Unit) {
    val magnitude = earthquake.magnitude
    val color = when {
        magnitude < 4.0 -> Color(0xFF4CAF50) // Green
        magnitude <= 6.0 -> Color(0xFFFFA500) // Orange
        else -> Color(0xFFF44336) // Red
    }
    
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "M ${String.format("%.1f", magnitude)}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = earthquake.place,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Depth: ${String.format("%.1f", earthquake.depth)} km",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun DisasterCard(disaster: DisasterEvent, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = disaster.category,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = disaster.title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "NASA EONET",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AlertScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Earthquake Alert", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Column(Modifier.padding(16.dp)) {
                SeverityBar(color = Color.Red)
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Magnitude: 5.4")
                    Text("Depth: 10km")
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Location: LA, US")
                    Text("Time: 2m ago")
                }
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = onBack) { Text("Dismiss") }
                    Button(onClick = { /* Share logic */ }) { Text("Share") }
                }
            }
        }
    }
}

@Composable
private fun SeverityBar(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(color = color, shape = MaterialTheme.shapes.small)
    )
}

@Composable
fun InsightsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Insights", style = MaterialTheme.typography.titleLarge)

        FrostedChartCard(title = "Earthquakes per Day") {
            AndroidView(factory = { ctx ->
                LineChart(ctx).apply {
                    val entries = (0..29).map { i -> Entry(i.toFloat(), (0..20).random().toFloat()) }
                    val dataSet = LineDataSet(entries, "Quakes/Day").apply {
                        color = 0xFF1976D2.toInt()
                        setDrawCircles(false)
                        lineWidth = 2f
                    }
                    data = LineData(dataSet)
                    description = Description().apply { text = "Last 30 days" }
                    legend.isEnabled = false
                    setTouchEnabled(false)
                }
            })
        }

        FrostedChartCard(title = "Magnitude Distribution") {
            AndroidView(factory = { ctx ->
                BarChart(ctx).apply {
                    val bins = listOf(2f,3f,4f,5f,6f)
                    val entries = bins.map { BarEntry(it, (2..20).random().toFloat()) }
                    val dataSet = BarDataSet(entries, "Magnitude Dist").apply { color = 0xFF2196F3.toInt() }
                    data = BarData(dataSet)
                    description = Description().apply { text = "Magnitude" }
                    legend.isEnabled = false
                    setTouchEnabled(false)
                }
            })
        }
    }
}



