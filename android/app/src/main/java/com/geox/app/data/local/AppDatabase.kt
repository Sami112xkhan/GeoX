package com.geox.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geox.app.data.model.DisasterEvent
import com.geox.app.data.model.EarthquakeEvent

@Database(
    entities = [EarthquakeEvent::class, DisasterEvent::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun earthquakeEventDao(): EarthquakeEventDao
    abstract fun disasterEventDao(): DisasterEventDao
}
