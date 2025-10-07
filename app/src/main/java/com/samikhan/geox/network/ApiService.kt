package com.samikhan.geox.network

import retrofit2.http.GET

data class UsgsFeatureCollection(
    val features: List<UsgsFeature>
)

data class UsgsFeature(
    val id: String,
    val properties: UsgsProperties,
    val geometry: UsgsGeometry?
)

data class UsgsProperties(
    val mag: Double?,
    val place: String?,
    val time: Long?
)

data class UsgsGeometry(
    val coordinates: List<Double>?
)

interface UsgsApiService {
    @GET("/earthquakes/feed/v1.0/summary/all_day.geojson")
    suspend fun getDailyEarthquakes(): UsgsFeatureCollection
}


