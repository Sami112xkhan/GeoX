package com.geox.app.data.repository

import com.geox.app.data.model.EarthquakeEvent
import kotlinx.coroutines.flow.Flow

interface EarthquakeRepository {
    fun getRecentEarthquakes(): Flow<List<EarthquakeEvent>>
    suspend fun getEventById(id: String): EarthquakeEvent?
    suspend fun refreshEarthquakes()
    suspend fun clearCache()
}
