@file:OptIn(ExperimentalMaterial3Api::class)

package com.geox.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geox.app.data.DisasterData
import com.geox.app.theme.*
import com.geox.app.ui.components.*
@Composable
fun HomeScreen(
    disasters: List<DisasterData>,
    onFilterClick: () -> Unit,
    onDisasterClick: (DisasterData) -> Unit,
    userLocation: Pair<Double, Double>? = null,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedDisaster by remember { mutableStateOf<DisasterData?>(null) }
    var isExpanded by remember { mutableStateOf(false) }
    var showNotifications by remember { mutableStateOf(false) }

    val filteredDisasters = disasters.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
        it.location.contains(searchQuery, ignoreCase = true)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar
            LiquidGlass(
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "GeoX",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    
                    Box {
                        IconButton(onClick = { showNotifications = true }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = TextSecondary
                            )
                        }
                        // Notification dot
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = 8.dp, y = 8.dp)
                                .size(8.dp)
                                .background(LimeGreen, CircleShape)
                        )
                    }
                }
            }

            // Map
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                MapView(
                    points = disasters,
                    userLocation = userLocation,
                    selectedDisaster = selectedDisaster,
                    onDisasterClick = onDisasterClick,
                    modifier = Modifier.fillMaxSize()
                )

                // Last updated pill
                LiquidGlass(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 16.dp),
                    subtle = true,
                    cornerRadius = 20.dp
                ) {
                    Text(
                        text = if (disasters.isEmpty()) "No disasters found" else "${disasters.size} disasters",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            // Bottom sheet
            BottomSheet(
                isExpanded = isExpanded,
                onExpandedChange = { isExpanded = it },
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                disasters = filteredDisasters,
                onDisasterClick = onDisasterClick
            )
        }

        // FAB - positioned above bottom sheet
        FloatingActionButton(
            onClick = onFilterClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-20).dp, y = (-390).dp)
                .size(56.dp),
            containerColor = LimeGreen,
            contentColor = TextPrimary,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Filters",
                tint = TextPrimary
            )
        }
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
    
    // Notifications dialog
    if (showNotifications) {
        AlertDialog(
            onDismissRequest = { showNotifications = false },
            title = { Text("Notifications", color = TextPrimary) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        "Recent alerts near you:",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                    if (disasters.isNotEmpty()) {
                        disasters.take(3).forEach { disaster ->
                            Text(
                                "• ${disaster.title}",
                                color = TextPrimary,
                                fontSize = 12.sp
                            )
                        }
                    } else {
                        Text(
                            "No recent alerts",
                            color = TextTertiary,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showNotifications = false }) {
                    Text("Close", color = LimeGreen)
                }
            },
            containerColor = GlassWhite,
            titleContentColor = TextPrimary,
            textContentColor = TextSecondary
        )
    }
}

@Composable
fun BottomSheet(
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    disasters: List<DisasterData>,
    onDisasterClick: (DisasterData) -> Unit
) {
    val height by animateDpAsState(
        targetValue = if (isExpanded) 560.dp else 360.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "height"
    )
    val scrollState = rememberScrollState()

    LiquidGlass(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        cornerRadius = 24.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // Handle
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
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
                        .clickable { onExpandedChange(!isExpanded) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Search
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Search disasters...", color = TextTertiary) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = TextTertiary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = GlassWhiteSubtle
                ),
                shape = RoundedCornerShape(20.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Disaster cards
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(disasters) { disaster ->
                    DisasterCard(
                        disaster = disaster,
                        onClick = { onDisasterClick(disaster) }
                    )
                }
            }
        }
    }
}

