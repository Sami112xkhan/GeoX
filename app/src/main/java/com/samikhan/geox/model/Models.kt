package com.samikhan.geox.model

data class Earthquake(
    val id: String,
    val magnitude: Double?,
    val place: String?,
    val timeMillis: Long?,
    val latitude: Double?,
    val longitude: Double?,
    val depthKm: Double?
)


