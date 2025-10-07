package com.samikhan.geox.data.repository.impl

import com.samikhan.geox.data.local.EarthquakeEventDao
import com.samikhan.geox.data.model.EarthquakeEvent
import com.samikhan.geox.data.model.UsgsFeature
import com.samikhan.geox.data.network.UsgsApi
import com.samikhan.geox.data.repository.EarthquakeRepository
import kotlinx.coroutines.flow.Flow
class NetworkEarthquakeRepository constructor(
    private val usgsApi: UsgsApi,
    private val earthquakeDao: EarthquakeEventDao
) : EarthquakeRepository {

    override fun getRecentEarthquakes(): Flow<List<EarthquakeEvent>> {
        return earthquakeDao.observeAll()
    }

    override suspend fun getEventById(id: String): EarthquakeEvent? {
        return earthquakeDao.getById(id)
    }

    override suspend fun refreshEarthquakes() {
        try {
            val response = usgsApi.getEarthquakes(UsgsApi.ALL_DAY)
            val events = response.features.map { it.toEarthquakeEvent() }
            earthquakeDao.insertAll(events)
        } catch (e: Exception) {
            // Handle network error - could emit error state
            throw e
        }
    }

    override suspend fun clearCache() {
        earthquakeDao.clearAll()
    }

    private fun UsgsFeature.toEarthquakeEvent(): EarthquakeEvent {
        val coordinates = geometry.coordinates
        return EarthquakeEvent(
            id = id,
            time = properties.time,
            latitude = coordinates[1], // GeoJSON format: [longitude, latitude, depth]
            longitude = coordinates[0],
            magnitude = properties.mag,
            depth = coordinates.getOrNull(2) ?: 0.0,
            place = properties.place,
            url = properties.url
        )
    }
}
