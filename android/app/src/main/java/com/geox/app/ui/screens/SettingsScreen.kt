package com.geox.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch
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
import androidx.compose.foundation.clickable
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass

@Composable
fun SettingsScreen(
    viewModel: com.geox.app.viewmodel.HomeViewModel? = null,
    onRefreshRateChange: ((String) -> Unit)? = null,
    userPreferences: com.geox.app.data.prefs.UserPreferences? = null,
    modifier: Modifier = Modifier
) {
    // Get settings from preferences if available
    val darkModePref = userPreferences?.darkMode?.collectAsState(initial = false)?.value ?: false
    val notificationsPref = userPreferences?.notifications?.collectAsState(initial = true)?.value ?: true
    val refreshRatePref = userPreferences?.refreshRate?.collectAsState(initial = "auto")?.value ?: "auto"
    
    var darkMode by remember { mutableStateOf(darkModePref) }
    var notifications by remember { mutableStateOf(notificationsPref) }
    var refreshRate by remember { mutableStateOf(refreshRatePref) }
    var showRefreshRateDialog by remember { mutableStateOf(false) }
    var showCacheClearedMessage by remember { mutableStateOf(false) }
    
    // Sync with preferences
    LaunchedEffect(darkModePref) { darkMode = darkModePref }
    LaunchedEffect(notificationsPref) { notifications = notificationsPref }
    LaunchedEffect(refreshRatePref) { refreshRate = refreshRatePref }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        SettingItem(
            icon = Icons.Default.DarkMode,
            title = "Dark Mode",
            description = if (darkMode) "Dark theme enabled" else "Light theme enabled",
            iconBgColor = TextTertiary.copy(alpha = 0.1f),
            rightContent = {
                Switch(
                    checked = darkMode,
                    onCheckedChange = { 
                        darkMode = it
                        userPreferences?.let { prefs ->
                            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                                prefs.setDarkMode(it)
                            }
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = LimeGreen,
                        checkedTrackColor = LimeGreen.copy(alpha = 0.5f)
                    )
                )
            }
        )

        SettingItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            description = if (notifications) "Alerts enabled" else "Alerts disabled",
            iconBgColor = WildfireOrange.copy(alpha = 0.1f),
            iconTint = WildfireOrange,
            rightContent = {
                Switch(
                    checked = notifications,
                    onCheckedChange = { 
                        notifications = it
                        userPreferences?.let { prefs ->
                            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                                prefs.setNotifications(it)
                            }
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = LimeGreen,
                        checkedTrackColor = LimeGreen.copy(alpha = 0.5f)
                    )
                )
            }
        )

        SettingItem(
            icon = Icons.Default.Delete,
            title = "Clear Cache",
            description = "Remove stored data",
            iconBgColor = EarthquakeRed.copy(alpha = 0.1f),
            iconTint = EarthquakeRed,
            rightContent = {
                TextButton(onClick = {
                    viewModel?.let {
                        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                            it.clearCache()
                            showCacheClearedMessage = true
                        }
                    }
                }) {
                    Text("Clear", color = TextPrimary)
                }
            }
        )

        SettingItem(
            icon = Icons.Default.FlashOn,
            title = "Simulate Event",
            description = "Test notifications",
            iconBgColor = Color(0xFFFCD34D).copy(alpha = 0.1f),
            iconTint = Color(0xFFFCD34D),
            rightContent = {
                TextButton(onClick = { 
                    // Could trigger a test notification here
                }) {
                    Text("Test", color = TextPrimary)
                }
            }
        )
        
        SettingItem(
            icon = Icons.Default.Speed,
            title = "Refresh Rate",
            description = "Screen refresh rate: ${refreshRate.replaceFirstChar { it.uppercaseChar() }}",
            iconBgColor = LimeGreen.copy(alpha = 0.1f),
            iconTint = LimeGreen,
            rightContent = {
                TextButton(onClick = { showRefreshRateDialog = true }) {
                    Text("Change", color = TextPrimary)
                }
            }
        )

        // Data sources info
        LiquidGlass(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = LimeGreen,
                    modifier = Modifier.size(20.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Data Sources",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = "• NASA EONET - Earth Observatory Natural Event Tracker",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "• USGS - Earthquake Hazards Program",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "• NOAA - National Oceanic and Atmospheric Administration",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        // Version
        Text(
            text = "v1.0 — GeoX Labs",
            fontSize = 12.sp,
            color = TextTertiary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        
        // Cache cleared message
        if (showCacheClearedMessage) {
            LaunchedEffect(showCacheClearedMessage) {
                kotlinx.coroutines.delay(2000)
                showCacheClearedMessage = false
            }
            LiquidGlass(
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 16.dp
            ) {
                Text(
                    text = "Cache cleared successfully",
                    modifier = Modifier.padding(16.dp),
                    color = LimeGreen,
                    fontSize = 14.sp
                )
            }
        }
    }
    
    // Refresh Rate Dialog
    if (showRefreshRateDialog) {
        Dialog(onDismissRequest = { showRefreshRateDialog = false }) {
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
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Select Refresh Rate",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    
                    listOf("auto", "60hz", "120hz").forEach { rate ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    refreshRate = rate
                                    userPreferences?.let { prefs ->
                                        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                                            prefs.setRefreshRate(rate)
                                        }
                                    }
                                    onRefreshRateChange?.invoke(rate)
                                    showRefreshRateDialog = false
                                }
                                .background(
                                    if (refreshRate == rate) LimeGreen.copy(alpha = 0.1f) else Color.Transparent,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = rate.replaceFirstChar { it.uppercaseChar() },
                                color = TextPrimary,
                                fontSize = 16.sp
                            )
                            if (refreshRate == rate) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = LimeGreen
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SettingItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    iconBgColor: Color,
    iconTint: Color = TextSecondary,
    rightContent: @Composable () -> Unit
) {
    LiquidGlass(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 24.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(iconBgColor, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
            rightContent()
        }
    }
}

