package com.samikhan.geox.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samikhan.geox.data.model.DisasterEvent

@Composable
fun EventCard(event: DisasterEvent, onClick: () -> Unit) {
    val icon: ImageVector
    val border: Color
    when (event.category.lowercase()) {
        "wildfires" -> { icon = Icons.Outlined.Warning; border = Color(0xFFFF7043) }
        "volcanoes" -> { icon = Icons.Outlined.Warning; border = Color(0xFFD32F2F) }
        "storms" -> { icon = Icons.Outlined.Warning; border = Color(0xFF29B6F6) }
        else -> { icon = Icons.Outlined.Place; border = Color(0xFF90CAF9) }
    }
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)),
        modifier = Modifier.border(1.dp, border.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
    ) {
        Column(Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = border)
            Text(event.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("${event.category} • ${formatTime(event.date)}", color = Color.LightGray)
        }
    }
}

private fun formatTime(ts: Long): String = java.text.SimpleDateFormat("MMM d, HH:mm", java.util.Locale.getDefault()).format(java.util.Date(ts))


