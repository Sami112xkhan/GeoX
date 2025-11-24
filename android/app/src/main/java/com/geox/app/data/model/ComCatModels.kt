package com.geox.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Response models for ComCat API
data class ComCatResponse(
    val type: String,
    val metadata: ComCatMetadata,
    val features: List<ComCatFeature>
)

data class ComCatMetadata(
    val generated: Long,
    val url: String,
    val title: String,
    val status: Int,
    val api: String,
    val count: Int
)

data class ComCatFeature(
    val type: String,
    val properties: ComCatProperties,
    val geometry: ComCatGeometry,
    val id: String
)

data class ComCatProperties(
    val mag: Double?,
    val place: String?,
    val time: Long,
    val updated: Long?,
    val tz: Int?,
    val url: String?,
    val detail: String?,
    val felt: Int?,
    val cdi: Double?,
    val mmi: Double?,
    val alert: String?,
    val status: String?,
    val tsunami: Int?,
    val sig: Int?,
    val net: String?,
    val code: String?,
    val ids: String?,
    val sources: String?,
    val types: String?,
    val nst: Int?,
    val dmin: Double?,
    val rms: Double?,
    val gap: Double?,
    val magType: String?,
    val type: String?,
    val title: String?
)

data class ComCatGeometry(
    val type: String,
    val coordinates: List<Double>
)

// Detail event response
data class ComCatDetailResponse(
    val type: String,
    val properties: ComCatDetailProperties,
    val geometry: ComCatGeometry,
    val id: String,
    val products: Map<String, List<ComCatProductResponse>>? = null
)

data class ComCatDetailProperties(
    val mag: Double?,
    val place: String?,
    val time: Long,
    val updated: Long?,
    val tz: Int?,
    val url: String?,
    val detail: String?,
    val felt: Int?,
    val cdi: Double?,
    val mmi: Double?,
    val alert: String?,
    val status: String?,
    val tsunami: Int?,
    val sig: Int?,
    val net: String?,
    val code: String?,
    val ids: String?,
    val sources: String?,
    val types: String?,
    val nst: Int?,
    val dmin: Double?,
    val rms: Double?,
    val gap: Double?,
    val magType: String?,
    val type: String?,
    val title: String?,
    val magError: Double?,
    val magNst: Int?,
    val horizontalError: Double?,
    val depthError: Double?,
    val locationSource: String?,
    val magSource: String?
)

data class ComCatProductResponse(
    val type: String,
    val properties: ComCatProductProperties,
    val contents: Map<String, ComCatContentResponse>? = null
)

data class ComCatProductProperties(
    val productType: String,
    val productCode: String,
    val source: String,
    val updateTime: Long,
    val version: String,
    val preferredWeight: Int
)

data class ComCatContentResponse(
    val url: String,
    val contentType: String,
    val length: Long,
    val lastModified: Long
)

// Search parameters for libcomcat API
data class EarthquakeSearchParams(
    val startTime: Long? = null,
    val endTime: Long? = null,
    val minLatitude: Double? = null,
    val maxLatitude: Double? = null,
    val minLongitude: Double? = null,
    val maxLongitude: Double? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val maxRadiusKm: Double? = null,
    val maxRadius: Double? = null,
    val minMagnitude: Double? = null,
    val maxMagnitude: Double? = null,
    val minDepth: Double? = null,
    val maxDepth: Double? = null,
    val catalog: String? = null,
    val contributor: String? = null,
    val eventType: String = "earthquake",
    val alertLevel: String? = null,
    val maxMmi: Double? = null,
    val minCdi: Double? = null,
    val maxCdi: Double? = null,
    val minFelt: Int? = null,
    val productType: String? = null,
    val reviewStatus: String? = null,
    val limit: Int? = null,
    val offset: Int = 1,
    val orderBy: String = "time-asc"
)
