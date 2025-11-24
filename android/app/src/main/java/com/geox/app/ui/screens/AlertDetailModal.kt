package com.geox.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import android.content.Intent
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.geox.app.data.DisasterData
import com.geox.app.data.DisasterType
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDetailModal(
    disaster: DisasterData,
    onDismiss: () -> Unit,
    onSourceClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    
    Dialog(onDismissRequest = onDismiss) {
        LiquidGlass(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            cornerRadius = 32.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Handle
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .background(
                                color = TextTertiary,
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                }

                // Close button
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextPrimary
                    )
                }

                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = getDisasterEmoji(disaster.type),
                        fontSize = 32.sp
                    )
                    Text(
                        text = disaster.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                // Badges
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    disaster.magnitude?.let {
                        Badge(
                            containerColor = EarthquakeRed.copy(alpha = 0.1f),
                            contentColor = EarthquakeRed
                        ) {
                            Text(
                                text = "Magnitude $it",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                    disaster.category?.let {
                        Badge(
                            containerColor = WildfireOrange.copy(alpha = 0.1f),
                            contentColor = WildfireOrange
                        ) {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // Details
                DetailRow(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    value = disaster.location,
                    subValue = "${disaster.coordinates.first.toFloat()}°, ${disaster.coordinates.second.toFloat()}°"
                )

                DetailRow(
                    icon = Icons.Default.Schedule,
                    label = "Time",
                    value = disaster.time
                )

                disaster.depth?.let {
                    DetailRow(
                        icon = Icons.Default.Layers,
                        label = "Depth",
                        value = it
                    )
                }

                disaster.description?.let {
                    LiquidGlass(
                        modifier = Modifier.fillMaxWidth(),
                        subtle = true,
                        cornerRadius = 20.dp
                    ) {
                        Text(
                            text = it,
                            fontSize = 12.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(16.dp),
                            lineHeight = 19.2.sp
                        )
                    }
                }

                // Map preview
                LiquidGlass(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    subtle = true,
                    cornerRadius = 20.dp
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        com.geox.app.ui.components.MapView(
                            points = listOf(disaster),
                            selectedDisaster = disaster,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        icon = Icons.Default.OpenInNew,
                        label = "Source",
                        onClick = {
                            // Open source URL based on disaster type
                            val url = when (disaster.type) {
                                DisasterType.EARTHQUAKE -> "https://earthquake.usgs.gov/earthquakes/eventpage/${disaster.id}"
                                else -> "https://eonet.gsfc.nasa.gov/api/v3/events/${disaster.id}"
                            }
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                // Handle error
                            }
                            onSourceClick()
                        },
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        icon = Icons.Default.Save,
                        label = "Save",
                        onClick = {
                            // Save disaster to favorites/bookmarks (implement with DataStore)
                            android.widget.Toast.makeText(
                                context,
                                "Disaster saved to favorites",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                            onSaveClick()
                        },
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        icon = Icons.Default.Share,
                        label = "Share",
                        onClick = {
                            val shareText = "${disaster.title}\n${disaster.location}\n${disaster.time}\n\n" +
                                    if (disaster.magnitude != null) "Magnitude: ${disaster.magnitude}\n" else "" +
                                    "Location: ${disaster.coordinates.first}°, ${disaster.coordinates.second}°"
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Share disaster info")
                            try {
                                context.startActivity(shareIntent)
                            } catch (e: Exception) {
                                // Handle error
                            }
                            onShareClick()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                // AI Prediction
                LiquidGlass(
                    modifier = Modifier.fillMaxWidth(),
                    subtle = true,
                    cornerRadius = 20.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "AI Prediction",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Next 7-day risk assessment",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "78%",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = LimeGreen
                            )
                            Text(
                                text = "Moderate Confidence",
                                fontSize = 12.sp,
                                color = TextSecondary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getDisasterEmoji(type: DisasterType): String {
    return when (type) {
        DisasterType.EARTHQUAKE -> "🔴"
        DisasterType.WILDFIRE -> "🔥"
        DisasterType.VOLCANO -> "🌋"
        DisasterType.FLOOD -> "🌊"
        DisasterType.STORM -> "⛈️"
    }
}

@Composable
fun DetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    subValue: String? = null
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    LimeGreen.copy(alpha = 0.1f),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = LimeGreen,
                modifier = Modifier.size(16.dp)
            )
        }
        Column {
            Text(
                text = label,
                fontSize = 11.sp,
                color = TextSecondary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            subValue?.let {
                Text(
                    text = it,
                    fontSize = 11.sp,
                    color = TextTertiary,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = TextPrimary
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Text(text = label, fontSize = 10.sp)
        }
    }
}

