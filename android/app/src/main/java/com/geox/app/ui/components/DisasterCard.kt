package com.geox.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geox.app.data.DisasterData
import com.geox.app.data.DisasterType
import com.geox.app.theme.*
import com.geox.app.ui.components.LiquidGlass

@Composable
fun DisasterCard(
    disaster: DisasterData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(targetValue = 1f, label = "scale")
    
    LiquidGlass(
        modifier = modifier
            .width(280.dp)
            .scale(scale)
            .clickable(onClick = onClick)
            .padding(16.dp),
        cornerRadius = 24.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = getDisasterColor(disaster.type),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getDisasterIcon(disaster.type),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = disaster.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    maxLines = 1
                )
                Text(
                    text = disaster.location,
                    fontSize = 12.sp,
                    color = TextSecondary,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = disaster.magnitude?.let { "M $it" } ?: disaster.category ?: "",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = getDisasterAccentColor(disaster.type)
                    )
                    Text(
                        text = disaster.time,
                        fontSize = 11.sp,
                        color = TextTertiary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun getDisasterColor(type: DisasterType): Color {
    return when (type) {
        DisasterType.EARTHQUAKE -> EarthquakeRed
        DisasterType.WILDFIRE -> WildfireOrange
        DisasterType.VOLCANO -> VolcanoPurple
        DisasterType.FLOOD -> FloodBlue
        DisasterType.STORM -> StormCyan
    }
}

@Composable
fun getDisasterAccentColor(type: DisasterType): Color {
    return when (type) {
        DisasterType.EARTHQUAKE -> Color(0xFFDC2626)
        DisasterType.WILDFIRE -> Color(0xFFEA580C)
        DisasterType.VOLCANO -> Color(0xFF9333EA)
        DisasterType.FLOOD -> Color(0xFF2563EB)
        DisasterType.STORM -> Color(0xFF0891B2)
    }
}

@Composable
fun getDisasterIcon(type: DisasterType): androidx.compose.ui.graphics.vector.ImageVector {
    return when (type) {
        DisasterType.EARTHQUAKE -> Icons.Default.Warning
        DisasterType.WILDFIRE -> Icons.Default.LocalFireDepartment
        DisasterType.VOLCANO -> Icons.Default.Landscape
        DisasterType.FLOOD -> Icons.Default.WaterDrop
        DisasterType.STORM -> Icons.Default.Thunderstorm
    }
}

