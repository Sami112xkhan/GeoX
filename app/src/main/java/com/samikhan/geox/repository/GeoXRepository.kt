package com.samikhan.geox.repository

import com.samikhan.geox.model.Earthquake
import com.samikhan.geox.network.UsgsApiService

class GeoXRepository(
    private val api: UsgsApiService
) {
    suspend fun fetchDailyEarthquakes(): List<Earthquake> {
        val fc = api.getDailyEarthquakes()
        return fc.features.map { f ->
            val coords = f.geometry?.coordinates
            Earthquake(
                id = f.id,
                magnitude = f.properties.mag,
                place = f.properties.place,
                timeMillis = f.properties.time,
                longitude = coords?.getOrNull(0),
                latitude = coords?.getOrNull(1),
                depthKm = coords?.getOrNull(2)
            )
        }
    }
}


