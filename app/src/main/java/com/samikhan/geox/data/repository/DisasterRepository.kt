package com.samikhan.geox.data.repository

import com.samikhan.geox.data.model.DisasterEvent
import kotlinx.coroutines.flow.Flow

interface DisasterRepository {
    fun getRecentDisasters(): Flow<List<DisasterEvent>>
    suspend fun getEventById(id: String): DisasterEvent?
    suspend fun refreshDisasters()
    suspend fun clearCache()
}
