package com.geox.app.data.repository

import android.util.Log
import com.geox.app.data.local.EarthquakeEventDao
import com.geox.app.data.model.*
import com.geox.app.data.network.ComCatApi
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class ComCatRepository(
    private val comCatApi: ComCatApi,
    private val earthquakeDao: EarthquakeEventDao
) {
    
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    
    fun getRecentEarthquakes(): Flow<List<EarthquakeEvent>> {
        return earthquakeDao.observeAll()
    }
    
    suspend fun getEventById(id: String): EarthquakeEvent? {
        Log.d("ComCatRepository", "Fetching event by ID: $id")
        return try {
            val detailResponse = comCatApi.getEventDetail(id)
            detailResponse?.toEarthquakeEvent()?.also { event ->
                earthquakeDao.insertAll(listOf(event))
                Log.d("ComCatRepository", "Fetched and cached detailed event: $id")
            }
        } catch (e: Exception) {
            Log.e("ComCatRepository", "Failed to fetch event by ID $id: ${e.message}", e)
            earthquakeDao.getById(id) // Fallback to cache if API fails
        }
    }
    
    suspend fun searchEarthquakes(params: EarthquakeSearchParams): List<EarthquakeEvent> {
        Log.d("ComCatRepository", "Searching earthquakes with params: $params")
        return try {
            val response = comCatApi.searchEarthquakes(
                startTime = params.startTime?.let { dateFormat.format(Date(it)) },
                endTime = params.endTime?.let { dateFormat.format(Date(it)) },
                minLatitude = params.minLatitude,
                maxLatitude = params.maxLatitude,
                minLongitude = params.minLongitude,
                maxLongitude = params.maxLongitude,
                latitude = params.latitude,
                longitude = params.longitude,
                maxRadiusKm = params.maxRadiusKm,
                maxRadius = params.maxRadius,
                minMagnitude = params.minMagnitude,
                maxMagnitude = params.maxMagnitude,
                minDepth = params.minDepth,
                maxDepth = params.maxDepth,
                catalog = params.catalog,
                contributor = params.contributor,
                eventType = params.eventType,
                alertLevel = params.alertLevel,
                maxMmi = params.maxMmi,
                minCdi = params.minCdi,
                maxCdi = params.maxCdi,
                minFelt = params.minFelt,
                productType = params.productType,
                reviewStatus = params.reviewStatus,
                limit = params.limit,
                offset = params.offset,
                orderBy = params.orderBy
            )
            Log.d("ComCatRepository", "Received search response: ${response.features.size} events")
            val events = response.features.map { it.toEarthquakeEvent() }
            earthquakeDao.insertAll(events)
            events
        } catch (e: Exception) {
            Log.e("ComCatRepository", "Search failed: ${e.message}", e)
            throw e
        }
    }
    
    suspend fun refreshEarthquakes() {
        Log.d("ComCatRepository", "Refreshing recent earthquakes...")
        try {
            val endTime = System.currentTimeMillis()
            val startTime = endTime - (7 * 24 * 60 * 60 * 1000) // 7 days ago
            
            val params = EarthquakeSearchParams(
                startTime = startTime,
                endTime = endTime,
                minMagnitude = 2.5,
                orderBy = "time-asc"
            )
            
            searchEarthquakes(params)
        } catch (e: Exception) {
            Log.e("ComCatRepository", "Refresh earthquakes failed: ${e.message}", e)
            throw e
        }
    }
    
    suspend fun clearCache() {
        earthquakeDao.clearAll()
    }
    
    private fun ComCatFeature.toEarthquakeEvent(): EarthquakeEvent {
        val coordinates = geometry.coordinates
        return EarthquakeEvent(
            id = id,
            time = properties.time,
            latitude = coordinates[1], // GeoJSON format: [longitude, latitude, depth]
            longitude = coordinates[0],
            magnitude = properties.mag ?: 0.0,
            depth = coordinates.getOrNull(2) ?: 0.0,
            place = properties.place ?: "Unknown",
            url = properties.url ?: "",
            source = "ComCat",
            alert = properties.alert,
            magType = properties.magType,
            gap = properties.gap,
            dmin = properties.dmin,
            rms = properties.rms,
            net = properties.net,
            nst = properties.nst,
            updated = properties.updated,
            detail = properties.detail,
            felt = properties.felt,
            cdi = properties.cdi,
            mmi = properties.mmi,
            alertLevel = properties.alert,
            status = properties.status,
            tsunami = properties.tsunami,
            sig = properties.sig,
            code = properties.code,
            ids = properties.ids,
            sources = properties.sources,
            types = properties.types,
            ncd = null
        )
    }

    private fun ComCatDetailResponse.toEarthquakeEvent(): EarthquakeEvent {
        val coordinates = geometry.coordinates
        val properties = properties
        return EarthquakeEvent(
            id = id,
            time = properties.time,
            latitude = coordinates[1],
            longitude = coordinates[0],
            magnitude = properties.mag ?: 0.0,
            depth = coordinates.getOrNull(2) ?: 0.0,
            place = properties.place ?: "Unknown",
            url = properties.url ?: "",
            source = properties.net ?: "ComCat",
            alert = properties.alert,
            magType = properties.magType,
            gap = properties.gap,
            dmin = properties.dmin,
            rms = properties.rms,
            net = properties.net,
            nst = properties.nst,
            updated = properties.updated,
            detail = properties.detail,
            felt = properties.felt,
            cdi = properties.cdi,
            mmi = properties.mmi,
            alertLevel = properties.alert,
            status = properties.status,
            tsunami = properties.tsunami,
            sig = properties.sig,
            code = properties.code,
            ids = properties.ids,
            sources = properties.sources,
            types = properties.types,
            ncd = null
        )
    }
}
