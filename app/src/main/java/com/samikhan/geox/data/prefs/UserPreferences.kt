package com.samikhan.geox.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFS_NAME = "geox_prefs"
val Context.dataStore by preferencesDataStore(name = PREFS_NAME)

object PrefKeys {
    val REGION: Preferences.Key<String> = stringPreferencesKey("region")
    val MIN_MAG: Preferences.Key<Double> = doublePreferencesKey("min_mag")
    val DARK_MODE: Preferences.Key<Boolean> = booleanPreferencesKey("dark_mode")
    val NOTIFICATIONS: Preferences.Key<Boolean> = booleanPreferencesKey("notifications")
}

data class UserPrefs(
    val region: String = "Global",
    val minMag: Double = 4.0,
    val darkMode: Boolean = false,
    val notifications: Boolean = true
)

class UserPreferences(private val context: Context) {
    val flow: Flow<UserPrefs> = context.dataStore.data.map { prefs ->
        UserPrefs(
            region = prefs[PrefKeys.REGION] ?: "Global",
            minMag = prefs[PrefKeys.MIN_MAG] ?: 4.0,
            darkMode = prefs[PrefKeys.DARK_MODE] ?: false,
            notifications = prefs[PrefKeys.NOTIFICATIONS] ?: true
        )
    }

    suspend fun setRegion(value: String) {
        context.dataStore.edit { it[PrefKeys.REGION] = value }
    }

    suspend fun setMinMag(value: Double) {
        context.dataStore.edit { it[PrefKeys.MIN_MAG] = value }
    }

    suspend fun setDarkMode(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.DARK_MODE] = value }
    }

    suspend fun setNotifications(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.NOTIFICATIONS] = value }
    }
}


