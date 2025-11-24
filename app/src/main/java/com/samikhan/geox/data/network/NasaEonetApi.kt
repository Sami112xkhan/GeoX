package com.samikhan.geox.data.network

import com.samikhan.geox.data.model.EonetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaEonetApi {
    @GET("api/v3/events")
    suspend fun getEvents(
        @Query("limit") limit: Int = 50,
        @Query("days") days: Int = 30,
        @Query("category") category: String? = null,
        @Query("status") status: String = "open"
    ): EonetResponse
    
    @GET("api/v3/categories")
    suspend fun getCategories(): List<Map<String, Any>>
    
    companion object {
        const val BASE_URL = "https://eonet.gsfc.nasa.gov/"
        
        // Event categories
        const val WILDFIRE = "wildfires"
        const val STORM = "seaLakeIce"
        const val VOLCANO = "volcanoes"
        const val FLOOD = "floods"
        const val DROUGHT = "drought"
        const val DUST_HAZE = "dustHaze"
        const val SNOW = "snow"
        const val LANDSLIDE = "landslides"
        const val MANMADE = "manmade"
        const val SEA_LAKE_ICE = "seaLakeIce"
    }
}
