package com.geox.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            LimeGreen.copy(alpha = 0.8f),
                            LimeGreen.copy(alpha = 0.6f)
                        )
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Public,
                contentDescription = null,
                tint = TextPrimary,
                modifier = Modifier.size(40.dp)
            )
        }

        Text(
            text = "GeoX",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Text(
            text = "Disaster Intelligence. Simplified.",
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Version 1.0.0",
            fontSize = 12.sp,
            color = TextTertiary,
            fontWeight = FontWeight.Medium
        )

        // Mission
        LiquidGlass(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Our Mission",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = "GeoX provides real-time disaster intelligence to help communities stay informed and prepared. We aggregate data from trusted sources like NASA and USGS to deliver accurate, timely information about natural events worldwide.",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 19.2.sp
                )
            }
        }

        // Features
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
                    text = "Features",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                FeatureItem(
                    icon = Icons.Default.Public,
                    title = "Real-time Tracking",
                    description = "Monitor disasters as they happen globally",
                    color = LimeGreen
                )
                FeatureItem(
                    icon = Icons.Default.FlashOn,
                    title = "AI Predictions",
                    description = "Advanced forecasting for disaster hotspots",
                    color = Color(0xFFFCD34D)
                )
                FeatureItem(
                    icon = Icons.Default.Shield,
                    title = "Trusted Sources",
                    description = "Data from NASA EONET, USGS, and NOAA",
                    color = Color(0xFF22C55E)
                )
            }
        }

        // Data Sources
        LiquidGlass(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Data Sources",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                DataSource(name = "NASA EONET", description = "Earth Observatory Natural Event Tracker", url = "eonet.gsfc.nasa.gov")
                DataSource(name = "USGS Earthquake", description = "United States Geological Survey", url = "earthquake.usgs.gov")
                DataSource(name = "NOAA", description = "National Oceanic and Atmospheric Administration", url = "noaa.gov")
            }
        }

        // Footer
        LiquidGlass(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = EarthquakeRed,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Built with care by GeoX Labs",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Text(
                    text = "© 2025 GeoX Labs. All rights reserved.",
                    fontSize = 10.sp,
                    color = TextTertiary
                )
            }
        }

        Text(
            text = "Contact: hello@geox.app",
            fontSize = 12.sp,
            color = TextTertiary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FeatureItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    color: Color
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Text(
                text = description,
                fontSize = 11.sp,
                color = TextSecondary
            )
        }
    }
}

@Composable
fun DataSource(
    name: String,
    description: String,
    url: String
) {
    LiquidGlass(
        modifier = Modifier.fillMaxWidth(),
        subtle = true,
        cornerRadius = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Text(
                text = description,
                fontSize = 10.sp,
                color = TextSecondary
            )
            Text(
                text = url,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = LimeGreen
            )
        }
    }
}

