package com.geox.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.geox.app.data.DisasterTypeFilters
import com.geox.app.data.FilterState
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersScreen(
    onClose: () -> Unit,
    onApply: (FilterState) -> Unit,
    initialFilters: FilterState? = null,
    modifier: Modifier = Modifier
) {
    var minMagnitude by remember { mutableStateOf(initialFilters?.minMagnitude ?: 2f) }
    var radius by remember { mutableStateOf((initialFilters?.radius ?: 100).toString()) }
    var types by remember {
        mutableStateOf(
            initialFilters?.types ?: DisasterTypeFilters(
                volcano = true,
                wildfire = true,
                flood = true,
                storm = true,
                earthquake = true
            )
        )
    }

    Dialog(onDismissRequest = onClose) {
        LiquidGlass(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            cornerRadius = 32.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Filters",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextPrimary
                        )
                    }
                }

                // Magnitude slider
                LiquidGlass(
                    modifier = Modifier.fillMaxWidth(),
                    subtle = true,
                    cornerRadius = 20.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Minimum Magnitude",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSecondary
                            )
                            Text(
                                text = String.format("%.1f", minMagnitude),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = LimeGreen,
                                modifier = Modifier
                                    .background(
                                        color = LimeGreen.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Slider(
                            value = minMagnitude,
                            onValueChange = { minMagnitude = it },
                            valueRange = 0f..8f,
                            colors = SliderDefaults.colors(
                                thumbColor = LimeGreen,
                                activeTrackColor = LimeGreen
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("0", fontSize = 10.sp, color = TextTertiary)
                            Text("8", fontSize = 10.sp, color = TextTertiary)
                        }
                    }
                }

                // Radius selector
                LiquidGlass(
                    modifier = Modifier.fillMaxWidth(),
                    subtle = true,
                    cornerRadius = 20.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Search Radius",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary
                        )
                        val radiusOptions = listOf("50", "100", "200", "500", "1000")
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(radiusOptions) { option ->
                                FilterChip(
                                    selected = radius == option,
                                    onClick = { radius = option },
                                    label = { Text("${option} km") }
                                )
                            }
                        }
                    }
                }

                // Disaster types
                Text(
                    text = "Disaster Types",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TypeToggle(
                        label = "Volcano",
                        icon = Icons.Default.Landscape,
                        color = VolcanoPurple,
                        active = types.volcano,
                        onClick = { types = types.copy(volcano = !types.volcano) },
                        modifier = Modifier.weight(1f)
                    )
                    TypeToggle(
                        label = "Wildfire",
                        icon = Icons.Default.LocalFireDepartment,
                        color = WildfireOrange,
                        active = types.wildfire,
                        onClick = { types = types.copy(wildfire = !types.wildfire) },
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TypeToggle(
                        label = "Flood",
                        icon = Icons.Default.WaterDrop,
                        color = FloodBlue,
                        active = types.flood,
                        onClick = { types = types.copy(flood = !types.flood) },
                        modifier = Modifier.weight(1f)
                    )
                    TypeToggle(
                        label = "Storm",
                        icon = Icons.Default.Thunderstorm,
                        color = StormCyan,
                        active = types.storm,
                        onClick = { types = types.copy(storm = !types.storm) },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Apply button
                Button(
                    onClick = {
                        val newFilters = FilterState(
                            minMagnitude = minMagnitude,
                            radius = radius.toIntOrNull() ?: 0, // Default to 0 (show all) if invalid
                            types = types
                        )
                        onApply(newFilters)
                        onClose()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LimeGreen,
                        contentColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "Apply Filters",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun TypeToggle(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    active: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = if (active) 1f else 0.95f,
        label = "scale"
    )

    LiquidGlass(
        modifier = modifier
            .scale(scale)
            .clickable(onClick = onClick),
        subtle = true,
        cornerRadius = 20.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = color.copy(alpha = if (active) 1f else 0.4f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (active) TextPrimary else TextTertiary
            )
        }
    }
}

