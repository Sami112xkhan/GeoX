package com.samikhan.geox.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samikhan.geox.data.model.DisasterEvent
import com.samikhan.geox.data.model.EarthquakeEvent
import com.samikhan.geox.ui.components.FrostedCard
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDetailScreen(
    event: Any, // Can be EarthquakeEvent or DisasterEvent
    onBack: () -> Unit,
    onShare: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Event Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        val shareText = when (event) {
                            is EarthquakeEvent -> "M${event.magnitude} earthquake near ${event.place}"
                            is DisasterEvent -> "${event.title} - ${event.category}"
                            else -> "Disaster event"
                        }
                        onShare(shareText)
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (event) {
                is EarthquakeEvent -> {
                    EarthquakeDetailCard(event = event)
                }
                is DisasterEvent -> {
                    DisasterDetailCard(event = event)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Close")
                }
                
                Button(
                    onClick = { 
                        val shareText = when (event) {
                            is EarthquakeEvent -> "M${event.magnitude} earthquake near ${event.place}"
                            is DisasterEvent -> "${event.title} - ${event.category}"
                            else -> "Disaster event"
                        }
                        onShare(shareText)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Share")
                }
            }
        }
    }
}

@Composable
private fun EarthquakeDetailCard(event: EarthquakeEvent) {
    FrostedCard(
        modifier = Modifier.fillMaxWidth(),
        title = "Earthquake Alert",
        subtitle = event.place
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            
            
            // Magnitude indicator
            val magnitude = event.magnitude
            val severityColor = when {
                magnitude < 4.0 -> Color(0xFF4CAF50) // Green
                magnitude <= 6.0 -> Color(0xFFFFA500) // Orange
                else -> Color(0xFFF44336) // Red
            }
            
            Card(
                colors = CardDefaults.cardColors(containerColor = severityColor.copy(alpha = 0.1f))
            ) {
                Text(
                    text = "Magnitude: M ${String.format("%.1f", magnitude)}",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = severityColor
                )
            }
            
            DetailRow("Location", event.place)
            DetailRow("Depth", "${String.format("%.1f", event.depth)} km")
            DetailRow("Coordinates", "${String.format("%.3f", event.latitude)}, ${String.format("%.3f", event.longitude)}")
            DetailRow("Time", formatTimestamp(event.time))
            DetailRow("Source", "USGS")
        }
    }
}

@Composable
private fun DisasterDetailCard(event: DisasterEvent) {
    FrostedCard(
        modifier = Modifier.fillMaxWidth(),
        title = event.title,
        subtitle = event.category
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            
            
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = "Category: ${event.category}",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            event.description?.let { description ->
                DetailRow("Description", description)
            }
            
            DetailRow("Coordinates", "${String.format("%.3f", event.latitude)}, ${String.format("%.3f", event.longitude)}")
            DetailRow("Date", formatTimestamp(event.date))
            DetailRow("Source", "NASA EONET")
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun formatTimestamp(timestamp: Long): String {
    return SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
        .format(java.util.Date(timestamp))
}
