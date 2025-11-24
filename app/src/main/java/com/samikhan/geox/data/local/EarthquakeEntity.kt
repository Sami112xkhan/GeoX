package com.samikhan.geox.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "earthquakes")
data class EarthquakeEntity(
    @PrimaryKey val id: String,
    val magnitude: Double?,
    val place: String?,
    val timeMillis: Long?,
    val latitude: Double?,
    val longitude: Double?,
    val depthKm: Double?
)


