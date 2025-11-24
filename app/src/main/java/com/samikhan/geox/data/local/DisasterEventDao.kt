package com.samikhan.geox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samikhan.geox.data.model.DisasterEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface DisasterEventDao {
    @Query("SELECT * FROM disaster_events ORDER BY date DESC")
    fun observeAll(): Flow<List<DisasterEvent>>

    @Query("SELECT * FROM disaster_events WHERE id = :id")
    suspend fun getById(id: String): DisasterEvent?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<DisasterEvent>)

    @Query("DELETE FROM disaster_events")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM disaster_events")
    suspend fun getCount(): Int
}
