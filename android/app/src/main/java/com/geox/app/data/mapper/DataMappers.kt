package com.geox.app.data.mapper

import com.geox.app.data.DisasterData
import com.geox.app.data.DisasterType
import com.geox.app.data.model.DisasterEvent
import com.geox.app.data.model.EarthquakeEvent
import java.text.SimpleDateFormat
import java.util.*

fun EarthquakeEvent.toDisasterData(): DisasterData {
    val timeAgo = formatTimeAgo(time)
    return DisasterData(
        id = id,
        type = DisasterType.EARTHQUAKE,
        title = "Magnitude ${String.format("%.1f", magnitude)} Earthquake",
        magnitude = magnitude.toFloat(),
        location = place,
        time = timeAgo,
        coordinates = Pair(latitude, longitude),
        description = "Depth: ${String.format("%.1f", depth)} km",
        depth = "${String.format("%.1f", depth)} km"
    )
}

fun DisasterEvent.toDisasterData(): DisasterData {
    val timeAgo = formatTimeAgo(date)
    val disasterType = when (category.lowercase()) {
        "wildfires" -> DisasterType.WILDFIRE
        "volcanoes" -> DisasterType.VOLCANO
        "floods" -> DisasterType.FLOOD
        "sealaiceice", "storms" -> DisasterType.STORM
        else -> DisasterType.EARTHQUAKE // Default fallback
    }
    
    return DisasterData(
        id = id,
        type = disasterType,
        title = title,
        category = category,
        location = extractLocationFromTitle(title),
        time = timeAgo,
        coordinates = Pair(latitude, longitude),
        description = description
    )
}

private fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    
    return when {
        diff < 60 * 1000 -> "${diff / 1000} seconds ago"
        diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)} minutes ago"
        diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)} hours ago"
        diff < 30 * 24 * 60 * 60 * 1000L -> "${diff / (24 * 60 * 60 * 1000)} days ago"
        else -> {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            dateFormat.format(Date(timestamp))
        }
    }
}

private fun extractLocationFromTitle(title: String): String {
    // Try to extract location from title, fallback to "Unknown"
    return title.split(",").lastOrNull()?.trim() ?: "Unknown"
}
