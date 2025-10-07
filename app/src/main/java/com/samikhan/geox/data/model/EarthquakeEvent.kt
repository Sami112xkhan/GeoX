package com.samikhan.geox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "earthquake_events")
data class EarthquakeEvent(
    @PrimaryKey val id: String,
    val time: Long,
    val latitude: Double,
    val longitude: Double,
    val magnitude: Double,
    val depth: Double,
    val place: String,
    val url: String,
    val source: String = "USGS"
)

data class UsgsFeature(
    val id: String,
    val properties: UsgsProperties,
    val geometry: UsgsGeometry
)

data class UsgsProperties(
    val mag: Double,
    val place: String,
    val time: Long,
    val url: String,
    val detail: String?
)

data class UsgsGeometry(
    val type: String,
    val coordinates: List<Double>
)

data class UsgsResponse(
    val type: String,
    val metadata: UsgsMetadata,
    val features: List<UsgsFeature>
)

data class UsgsMetadata(
    val generated: Long,
    val url: String,
    val title: String,
    val status: Int,
    val api: String,
    val count: Int
)
