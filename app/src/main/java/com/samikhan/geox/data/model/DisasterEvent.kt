package com.samikhan.geox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disaster_events")
data class DisasterEvent(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val category: String,
    val latitude: Double,
    val longitude: Double,
    val date: Long,
    val url: String?,
    val source: String = "NASA_EONET"
)

data class EonetEvent(
    val id: String,
    val title: String,
    val description: String?,
    val categories: List<EonetCategory>,
    val geometry: List<EonetGeometry>,
    val sources: List<EonetSource>?,
    val date: String
)

data class EonetCategory(
    val id: Int,
    val title: String
)

data class EonetGeometry(
    val date: String,
    val type: String,
    val coordinates: List<Double>
)

data class EonetSource(
    val id: String,
    val url: String
)

data class EonetResponse(
    val title: String,
    val description: String,
    val link: String,
    val events: List<EonetEvent>
)
