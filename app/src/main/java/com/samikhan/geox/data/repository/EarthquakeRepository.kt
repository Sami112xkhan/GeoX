package com.samikhan.geox.data.repository

import com.samikhan.geox.data.model.EarthquakeEvent
import kotlinx.coroutines.flow.Flow

interface EarthquakeRepository {
    fun getRecentEarthquakes(): Flow<List<EarthquakeEvent>>
    suspend fun getEventById(id: String): EarthquakeEvent?
    suspend fun refreshEarthquakes()
    suspend fun clearCache()
}
