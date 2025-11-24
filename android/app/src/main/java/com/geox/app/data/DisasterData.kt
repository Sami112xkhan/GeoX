package com.geox.app.data

data class DisasterData(
    val id: String,
    val type: DisasterType,
    val title: String,
    val magnitude: Float? = null,
    val category: String? = null,
    val location: String,
    val time: String,
    val coordinates: Pair<Double, Double>,
    val description: String? = null,
    val depth: String? = null
)

enum class DisasterType {
    EARTHQUAKE,
    WILDFIRE,
    VOLCANO,
    FLOOD,
    STORM
}

