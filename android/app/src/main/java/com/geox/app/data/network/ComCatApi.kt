package com.geox.app.data.network

import com.geox.app.data.model.*
import retrofit2.http.*

interface ComCatApi {
    
    // Basic search endpoint
    @GET("fdsnws/event/1/query")
    suspend fun searchEarthquakes(
        @Query("format") format: String = "geojson",
        @Query("starttime") startTime: String? = null,
        @Query("endtime") endTime: String? = null,
        @Query("minlatitude") minLatitude: Double? = null,
        @Query("maxlatitude") maxLatitude: Double? = null,
        @Query("minlongitude") minLongitude: Double? = null,
        @Query("maxlongitude") maxLongitude: Double? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null,
        @Query("maxradiuskm") maxRadiusKm: Double? = null,
        @Query("maxradius") maxRadius: Double? = null,
        @Query("minmagnitude") minMagnitude: Double? = null,
        @Query("maxmagnitude") maxMagnitude: Double? = null,
        @Query("mindepth") minDepth: Double? = null,
        @Query("maxdepth") maxDepth: Double? = null,
        @Query("catalog") catalog: String? = null,
        @Query("contributor") contributor: String? = null,
        @Query("eventtype") eventType: String = "earthquake",
        @Query("alertlevel") alertLevel: String? = null,
        @Query("maxmmi") maxMmi: Double? = null,
        @Query("mincdi") minCdi: Double? = null,
        @Query("maxcdi") maxCdi: Double? = null,
        @Query("minfelt") minFelt: Int? = null,
        @Query("producttype") productType: String? = null,
        @Query("reviewstatus") reviewStatus: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int = 1,
        @Query("orderby") orderBy: String = "time-asc"
    ): ComCatResponse
    
    // Count endpoint
    @GET("fdsnws/event/1/count")
    suspend fun countEarthquakes(
        @Query("format") format: String = "geojson",
        @Query("starttime") startTime: String? = null,
        @Query("endtime") endTime: String? = null,
        @Query("minlatitude") minLatitude: Double? = null,
        @Query("maxlatitude") maxLatitude: Double? = null,
        @Query("minlongitude") minLongitude: Double? = null,
        @Query("maxlongitude") maxLongitude: Double? = null,
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null,
        @Query("maxradiuskm") maxRadiusKm: Double? = null,
        @Query("maxradius") maxRadius: Double? = null,
        @Query("minmagnitude") minMagnitude: Double? = null,
        @Query("maxmagnitude") maxMagnitude: Double? = null,
        @Query("mindepth") minDepth: Double? = null,
        @Query("maxdepth") maxDepth: Double? = null,
        @Query("catalog") catalog: String? = null,
        @Query("contributor") contributor: String? = null,
        @Query("eventtype") eventType: String = "earthquake",
        @Query("alertlevel") alertLevel: String? = null,
        @Query("maxmmi") maxMmi: Double? = null,
        @Query("mincdi") minCdi: Double? = null,
        @Query("maxcdi") maxCdi: Double? = null,
        @Query("minfelt") minFelt: Int? = null,
        @Query("producttype") productType: String? = null,
        @Query("reviewstatus") reviewStatus: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int = 1,
        @Query("orderby") orderBy: String = "time-asc"
    ): ComCatResponse
    
    // Get event by ID
    @GET("fdsnws/event/1/query")
    suspend fun getEventById(
        @Query("format") format: String = "geojson",
        @Query("eventid") eventId: String,
        @Query("catalog") catalog: String? = null,
        @Query("includesuperseded") includeSuperseded: Boolean = false,
        @Query("includedeleted") includeDeleted: Boolean = false
    ): ComCatResponse
    
    // Get detailed event information
    @GET("earthquakes/feed/v1.0/detail/{eventId}.geojson")
    suspend fun getEventDetail(
        @Path("eventId") eventId: String
    ): ComCatDetailResponse
    
    companion object {
        const val BASE_URL = "https://earthquake.usgs.gov/"
    }
}
