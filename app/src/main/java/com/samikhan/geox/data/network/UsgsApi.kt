package com.samikhan.geox.data.network

import com.samikhan.geox.data.model.UsgsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UsgsApi {
    @GET("earthquakes/feed/v1.0/summary/{feed}.geojson")
    suspend fun getEarthquakes(@Path("feed") feed: String): UsgsResponse
    
    companion object {
        const val BASE_URL = "https://earthquake.usgs.gov/"
        
        // Available feeds
        const val ALL_DAY = "all_day"
        const val ALL_WEEK = "all_week"
        const val ALL_MONTH = "all_month"
        const val SIGNIFICANT_DAY = "significant_day"
        const val SIGNIFICANT_WEEK = "significant_week"
        const val SIGNIFICANT_MONTH = "significant_month"
    }
}
