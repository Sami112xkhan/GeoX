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
    val disasters = viewModel.disasters.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    var region by remember { mutableStateOf("global") }
    val now = System.currentTimeMillis()

    val strongestQuake = remember(quakes) { quakes.maxByOrNull { it.magnitude } }
    val avgMagnitude = remember(quakes) {
        quakes.takeIf { it.isNotEmpty() }?.map { it.magnitude }?.average()
    }
    val avgDepth = remember(quakes) {
        quakes.takeIf { it.isNotEmpty() }?.map { it.depth }?.average()
    }
    val last24hCount = remember(quakes) {
        quakes.count { now - it.time <= 24 * 60 * 60 * 1000 }
    }
    val significantQuakes = remember(quakes) {
        quakes.sortedByDescending { it.magnitude }.take(3)
    }
    val totalEvents = remember(quakes, disasters) { quakes.size + disasters.size }
    val categoryBreakdown = remember(disasters) {
        disasters
            .groupingBy { it.category }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(4)
    }
    
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

        // Snapshot metrics
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InsightMetricCard(
                    title = "Strongest quake",
                    value = strongestQuake?.magnitude?.format(1) ?: "--",
                    subtitle = strongestQuake?.place ?: "No recent data",
                    accent = EarthquakeRed,
                    modifier = Modifier.weight(1f)
                )
                InsightMetricCard(
                    title = "Avg magnitude",
                    value = avgMagnitude?.format(1) ?: "--",
                    subtitle = "$last24hCount events in 24h",
                    accent = LimeGreen,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InsightMetricCard(
                    title = "Avg depth",
                    value = avgDepth?.let { "${it.format(0)} km" } ?: "--",
                    subtitle = "Based on ${quakes.size} quakes",
                    accent = FloodBlue,
                    modifier = Modifier.weight(1f)
                )
                InsightMetricCard(
                    title = "Tracked events",
                    value = totalEvents.toString(),
                    subtitle = "Earth & space sources",
                    accent = StormCyan,
                    modifier = Modifier.weight(1f)
                )
            }
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
            if (categoryBreakdown.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No active disaster data yet", color = TextSecondary)
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val total = categoryBreakdown.sumOf { it.value }.coerceAtLeast(1)
                    categoryBreakdown.forEach { (category, count) ->
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(category, color = TextPrimary, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                Text("$count active", color = TextSecondary, fontSize = 12.sp)
                            }
                            LinearProgressIndicator(
                                progress = count / total.toFloat(),
                                color = categoryColor(category),
                                trackColor = categoryColor(category).copy(alpha = 0.2f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

        // Significant events
        ChartCard(
            title = "Significant Events",
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                significantQuakes.forEachIndexed { index, quake ->
                    SignificantEventItem(
                        rank = index + 1,
                        title = quake.place,
                        magnitude = quake.magnitude,
                        depth = quake.depth,
                        timeAgo = timeAgo(now - quake.time)
                    )
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

@Composable
fun InsightMetricCard(
    title: String,
    value: String,
    subtitle: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    LiquidGlass(
        modifier = modifier,
        cornerRadius = 24.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = title, color = TextSecondary, fontSize = 12.sp)
            Text(
                text = value,
                color = accent,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = subtitle, color = TextPrimary, fontSize = 12.sp)
        }
    }
}

@Composable
fun SignificantEventItem(
    rank: Int,
    title: String,
    magnitude: Double,
    depth: Double,
    timeAgo: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(LimeGreen.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("#$rank", color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(title, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("M ${magnitude.format(1)} • ${depth.format(0)} km • $timeAgo", color = TextSecondary, fontSize = 11.sp)
        }
    }
}

private fun Double.format(decimals: Int): String = "%.${decimals}f".format(this)

private fun categoryColor(category: String): Color = when {
    category.contains("fire", ignoreCase = true) -> WildfireOrange
    category.contains("storm", ignoreCase = true) || category.contains("cyclone", ignoreCase = true) -> StormCyan
    category.contains("flood", ignoreCase = true) -> FloodBlue
    category.contains("volcano", ignoreCase = true) -> VolcanoPurple
    else -> EarthquakeRed
}

private fun timeAgo(durationMillis: Long): String {
    val hours = durationMillis / (1000 * 60 * 60)
    val days = hours / 24
    return when {
        hours < 1 -> "moments ago"
        hours < 24 -> "$hours h ago"
        else -> "${days}d ago"
    }
}

