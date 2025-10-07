package com.samikhan.geox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EarthquakeDao {
    @Query("SELECT * FROM earthquakes ORDER BY timeMillis DESC")
    fun observeAll(): Flow<List<EarthquakeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<EarthquakeEntity>)

    @Query("DELETE FROM earthquakes")
    suspend fun clearAll()
}


