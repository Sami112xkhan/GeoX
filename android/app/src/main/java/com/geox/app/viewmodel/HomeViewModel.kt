package com.geox.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geox.app.data.model.EarthquakeEvent
import com.geox.app.data.model.DisasterEvent
import com.geox.app.data.repository.DisasterRepository
import com.geox.app.data.repository.ComCatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val comCatRepository: ComCatRepository,
    private val disasterRepository: DisasterRepository
) : ViewModel() {

    private val _earthquakes = MutableStateFlow<List<EarthquakeEvent>>(emptyList())
    val earthquakes: StateFlow<List<EarthquakeEvent>> = _earthquakes.asStateFlow()

    private val _disasters = MutableStateFlow<List<DisasterEvent>>(emptyList())
    val disasters: StateFlow<List<DisasterEvent>> = _disasters.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        // Observe repository data
        viewModelScope.launch {
            comCatRepository.getRecentEarthquakes().collect { events ->
                _earthquakes.value = events
            }
        }

        viewModelScope.launch {
            disasterRepository.getRecentDisasters().collect { events ->
                _disasters.value = events
            }
        }
    }

    fun refreshEarthquakes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                comCatRepository.refreshEarthquakes()
            } catch (e: Exception) {
                // Handle error - could emit error state
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshDisasters() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                disasterRepository.refreshDisasters()
            } catch (e: Exception) {
                // Handle error - could emit error state
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshAll() {
        refreshEarthquakes()
        refreshDisasters()
    }

    suspend fun getEarthquakeById(id: String): EarthquakeEvent? {
        return comCatRepository.getEventById(id)
    }

    suspend fun getDisasterById(id: String): DisasterEvent? {
        return disasterRepository.getEventById(id)
    }

    suspend fun clearCache() {
        viewModelScope.launch {
            try {
                comCatRepository.clearCache()
                disasterRepository.clearCache()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
