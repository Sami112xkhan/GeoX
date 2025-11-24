package com.samikhan.geox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samikhan.geox.data.model.EarthquakeEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface EarthquakeEventDao {
    @Query("SELECT * FROM earthquake_events ORDER BY time DESC")
    fun observeAll(): Flow<List<EarthquakeEvent>>

    @Query("SELECT * FROM earthquake_events WHERE id = :id")
    suspend fun getById(id: String): EarthquakeEvent?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EarthquakeEvent>)

    @Query("DELETE FROM earthquake_events")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM earthquake_events")
    suspend fun getCount(): Int
}
