package com.samikhan.geox.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samikhan.geox.data.model.DisasterEvent
import com.samikhan.geox.data.model.EarthquakeEvent

@Database(
    entities = [EarthquakeEvent::class, DisasterEvent::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun earthquakeEventDao(): EarthquakeEventDao
    abstract fun disasterEventDao(): DisasterEventDao
}


