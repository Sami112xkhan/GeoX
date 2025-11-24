package com.geox.app.data

data class FilterState(
    val minMagnitude: Float = 2f,
    val radius: Int = 100,
    val types: DisasterTypeFilters = DisasterTypeFilters()
)

data class DisasterTypeFilters(
    val volcano: Boolean = true,
    val wildfire: Boolean = true,
    val flood: Boolean = true,
    val storm: Boolean = true,
    val earthquake: Boolean = true
)

