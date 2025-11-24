package com.geox.app.data.repository

import com.geox.app.data.local.DisasterEventDao
import com.geox.app.data.model.DisasterEvent
import com.geox.app.data.model.EonetEvent
import com.geox.app.data.network.NasaEonetApi
import kotlinx.coroutines.flow.Flow

class NetworkDisasterRepository constructor(
    private val nasaEonetApi: NasaEonetApi,
    private val disasterDao: DisasterEventDao
) : DisasterRepository {

    override fun getRecentDisasters(): Flow<List<DisasterEvent>> {
        return disasterDao.observeAll()
    }

    override suspend fun getEventById(id: String): DisasterEvent? {
        return disasterDao.getById(id)
    }

    override suspend fun refreshDisasters() {
        try {
            val response = nasaEonetApi.getEvents(limit = 50, days = 30)
            val events = response.events.map { it.toDisasterEvent() }
            disasterDao.insertAll(events)
        } catch (e: Exception) {
            // Handle network error - could emit error state
            throw e
        }
    }

    override suspend fun clearCache() {
        disasterDao.clearAll()
    }

    private fun EonetEvent.toDisasterEvent(): DisasterEvent {
        // Get the first geometry point for coordinates
        val geometry = geometry.firstOrNull()
        val coordinates = geometry?.coordinates ?: listOf(0.0, 0.0)
        
        return DisasterEvent(
            id = id,
            title = title,
            description = description,
            category = categories.firstOrNull()?.title ?: "Unknown",
            latitude = coordinates[1], // GeoJSON format: [longitude, latitude]
            longitude = coordinates[0],
            date = parseDate(date),
            url = sources?.firstOrNull()?.url
        )
    }

    private fun parseDate(dateString: String): Long {
        return try {
            java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", java.util.Locale.getDefault())
                .parse(dateString)?.time ?: System.currentTimeMillis()
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
}
