package com.samikhan.geox.data.repository.impl

import com.samikhan.geox.data.model.EarthquakeEvent
import com.samikhan.geox.data.repository.EarthquakeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
class FakeEarthquakeRepository constructor() : EarthquakeRepository {

    private val fakeEarthquakes = listOf(
        EarthquakeEvent(
            id = "fake1",
            time = System.currentTimeMillis() - 3600000, // 1 hour ago
            latitude = 37.7749,
            longitude = -122.4194,
            magnitude = 4.2,
            depth = 10.5,
            place = "San Francisco, CA",
            url = "https://earthquake.usgs.gov/earthquakes/eventpage/fake1"
        ),
        EarthquakeEvent(
            id = "fake2",
            time = System.currentTimeMillis() - 7200000, // 2 hours ago
            latitude = 35.6762,
            longitude = 139.6503,
            magnitude = 5.8,
            depth = 15.2,
            place = "Tokyo, Japan",
            url = "https://earthquake.usgs.gov/earthquakes/eventpage/fake2"
        ),
        EarthquakeEvent(
            id = "fake3",
            time = System.currentTimeMillis() - 10800000, // 3 hours ago
            latitude = -6.2088,
            longitude = 106.8456,
            magnitude = 3.9,
            depth = 8.1,
            place = "Jakarta, Indonesia",
            url = "https://earthquake.usgs.gov/earthquakes/eventpage/fake3"
        )
    )

    override fun getRecentEarthquakes(): Flow<List<EarthquakeEvent>> {
        return flowOf(fakeEarthquakes)
    }

    override suspend fun getEventById(id: String): EarthquakeEvent? {
        return fakeEarthquakes.find { it.id == id }
    }

    override suspend fun refreshEarthquakes() {
        // Fake implementation - no actual refresh needed
    }

    override suspend fun clearCache() {
        // Fake implementation - no cache to clear
    }
}
