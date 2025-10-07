package com.samikhan.geox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samikhan.geox.data.model.EarthquakeEvent
import com.samikhan.geox.data.model.DisasterEvent

@Composable
fun DisasterMap(
    earthquakes: List<EarthquakeEvent>,
    disasters: List<DisasterEvent>,
    modifier: Modifier = Modifier,
    onEventSelected: (Any) -> Unit = {}
) {
    var selectedEvent by remember { mutableStateOf<Any?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Light blue background
    ) {
        // Map placeholder with earthquake markers simulation
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "🗺️ Events Map", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color(0xFF90CAF9))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Events: ${earthquakes.size} earthquakes, ${disasters.size} disasters",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF424242)
            )
            
            // Simulate earthquake markers
            Spacer(modifier = Modifier.height(20.dp))
            earthquakes.take(3).forEach { earthquake ->
                val magnitude = earthquake.magnitude
                val color = when {
                    magnitude < 4.0 -> Color(0xFF4CAF50) // Green
                    magnitude <= 6.0 -> Color(0xFFFFA500) // Orange
                    else -> Color(0xFFF44336) // Red
                }
                
                Card(
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .height(40.dp)
                        .padding(vertical = 2.dp),
                    colors = androidx.compose.material3.CardDefaults.cardColors(
                        containerColor = color.copy(alpha = 0.3f)
                    ),
                    onClick = { selectedEvent = earthquake; onEventSelected(earthquake) }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "M ${String.format("%.1f", magnitude)} - ${earthquake.place}",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                    }
                }
            }
            
            // Simulate disaster markers
            disasters.take(2).forEach { disaster ->
                Card(
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .height(40.dp)
                        .padding(vertical = 2.dp),
                    colors = androidx.compose.material3.CardDefaults.cardColors(
                        containerColor = Color(0xFF2196F3).copy(alpha = 0.3f)
                    ),
                    onClick = { selectedEvent = disaster; onEventSelected(disaster) }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "${disaster.category} - ${disaster.title}",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        
        // Event details overlay
        selectedEvent?.let { event ->
            Card(
                modifier = Modifier
                    .fillMaxSize(0.4f)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    when (event) {
                        is EarthquakeEvent -> {
                            Text(
                                text = "Earthquake Details",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text("Magnitude: M ${String.format("%.1f", event.magnitude)}")
                            Text("Location: ${event.place}")
                            Text("Depth: ${String.format("%.1f km", event.depth)}")
                            Text("Time: ${java.text.SimpleDateFormat("MMM dd, yyyy HH:mm", java.util.Locale.getDefault()).format(java.util.Date(event.time))}")
                        }
                        is DisasterEvent -> {
                            Text(
                                text = "Disaster Details",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text("Category: ${event.category}")
                            Text("Title: ${event.title}")
                            event.description?.let { Text("Description: $it") }
                            Text("Date: ${java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault()).format(java.util.Date(event.date))}")
                        }
                    }
                }
            }
        }
    }
}