package com.geox.app.ui.screens

import android.graphics.Color as AColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass
import com.geox.app.viewmodel.HomeViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.EntryXComparator
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val quakes = viewModel.earthquakes.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    var region by remember { mutableStateOf("global") }
    
    // Wait for data to load
    LaunchedEffect(Unit) {
        if (quakes.isEmpty() && !isLoading) {
            viewModel.refreshEarthquakes()
        }
    }

    if (isLoading && quakes.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CircularProgressIndicator(color = LimeGreen)
                Text("Loading insights data...", color = TextSecondary)
            }
        }
    } else if (quakes.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("No data available", color = TextSecondary)
                TextButton(onClick = { viewModel.refreshEarthquakes() }) {
                    Text("Refresh", color = LimeGreen)
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Insights & Analytics",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            
            FilterChip(
                selected = true,
                onClick = { },
                label = { Text(region.replaceFirstChar { it.uppercaseChar() }) }
            )
        }

        // Frequency chart with real data
        ChartCard(
            title = "Earthquake Frequency (30 Days)",
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.height(250.dp).fillMaxWidth()) {
                AndroidView(
                    factory = { ctx ->
                        LineChart(ctx).apply {
                            setTouchEnabled(false)
                            legend.isEnabled = false
                            axisRight.isEnabled = false
                            xAxis.textColor = AColor.WHITE
                            axisLeft.textColor = AColor.WHITE
                            description = Description().apply { text = "" }
                            layoutParams = android.view.ViewGroup.LayoutParams(
                                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                                android.view.ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                    },
                    update = { chart ->
                // Group earthquakes by day for the last 30 days
                val calendar = Calendar.getInstance()
                val dayCounts = mutableMapOf<Int, Int>()
                val now = System.currentTimeMillis()
                
                quakes.forEach { quake ->
                    calendar.timeInMillis = quake.time
                    val daysAgo = ((now - quake.time) / (24 * 60 * 60 * 1000)).toInt()
                    if (daysAgo in 0..29) {
                        dayCounts[29 - daysAgo] = (dayCounts[29 - daysAgo] ?: 0) + 1
                    }
                }
                
                val entries = (0..29).map { day ->
                    Entry(day.toFloat(), (dayCounts[day] ?: 0).toFloat())
                }.sortedWith(EntryXComparator())
                
                val set = LineDataSet(entries, "").apply {
                    color = AColor.rgb(11, 87, 164)
                    setCircleColor(AColor.rgb(11, 87, 164))
                    lineWidth = 2f
                    circleRadius = 3f
                    setDrawValues(false)
                }
                chart.data = LineData(set)
                chart.invalidate()
            })
            }
        }

        // Magnitude distribution with real data
        ChartCard(
            title = "Magnitude Distribution",
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.height(250.dp).fillMaxWidth()) {
                AndroidView(
                    factory = { ctx ->
                        BarChart(ctx).apply {
                            setTouchEnabled(false)
                            legend.isEnabled = false
                            axisRight.isEnabled = false
                            description = Description().apply { text = "" }
                            xAxis.textColor = AColor.WHITE
                            axisLeft.textColor = AColor.WHITE
                            layoutParams = android.view.ViewGroup.LayoutParams(
                                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                                android.view.ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                    },
                    update = { chart ->
                // Group by magnitude bins
                val mags = quakes.groupingBy { 
                    it.magnitude.toInt().coerceIn(2, 9)
                }.eachCount()
                
                val bins = (2..9).map { it to (mags[it] ?: 0) }
                val entries = bins.map { BarEntry(it.first.toFloat(), it.second.toFloat()) }
                val set = BarDataSet(entries, "").apply {
                    color = AColor.rgb(11, 87, 164)
                    setDrawValues(false)
                }
                chart.data = BarData(set)
                chart.invalidate()
            })
            }
        }

        // Category pie chart placeholder
        ChartCard(
            title = "Disasters by Category",
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(
                    Pair("Earthquakes", EarthquakeRed),
                    Pair("Wildfires", WildfireOrange),
                    Pair("Storms", StormCyan),
                    Pair("Floods", FloodBlue)
                ).forEach { (label, color) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(color, CircleShape)
                        )
                        Text(
                            text = label,
                            fontSize = 10.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        // AI Forecast
        LiquidGlass(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "AI Forecast",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = "Predicted hotspots next week",
                    fontSize = 12.sp,
                    color = TextSecondary
                )

                // Mini heatmap
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(35) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(
                                    color = when (it % 7) {
                                        0, 6 -> EarthquakeRed.copy(alpha = 0.8f)
                                        1, 5 -> WildfireOrange.copy(alpha = 0.6f)
                                        else -> Color.Transparent
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LegendItem("High Risk", EarthquakeRed)
                    LegendItem("Medium Risk", WildfireOrange)
                    LegendItem("Low Risk", Color.Transparent)
                }
            }
        }
        }
    }
}

@Composable
fun ChartCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    LiquidGlass(
        modifier = modifier,
        cornerRadius = 24.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            content()
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, CircleShape)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = TextSecondary,
            fontWeight = FontWeight.Medium
        )
    }
}

