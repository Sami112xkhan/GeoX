package com.samikhan.geox.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.samikhan.geox.data.prefs.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onToggleDark: () -> Unit,
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val vm: SettingsViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(UserPreferences(context)) as T
        }
    })
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Region: ${state.region}")
                Button(onClick = { vm.setRegion(if (state.region == "Global") "US" else "Global") }) { Text("Toggle Region") }

                Text("Min Magnitude: ${String.format("%.1f", state.minMag)}")
                Slider(value = state.minMag.toFloat(), onValueChange = { vm.setMinMag(it.toDouble()) }, valueRange = 2f..7f, steps = 5)

                Text("Notifications")
                Switch(checked = state.notifications, onCheckedChange = { vm.setNotifications(it) })

                Text("Dark Mode")
                Switch(checked = state.darkMode, onCheckedChange = { vm.setDarkMode(it); onToggleDark() })
            }
        }
    }
}

class SettingsViewModel(private val prefs: UserPreferences) : ViewModel() {
    private val _state = kotlinx.coroutines.flow.MutableStateFlow(prefsSnapshot())
    val state: kotlinx.coroutines.flow.StateFlow<com.samikhan.geox.data.prefs.UserPrefs> = _state

    init {
        viewModelScope.launch {
            prefs.flow.collect { _state.value = it }
        }
    }

    private fun prefsSnapshot() = com.samikhan.geox.data.prefs.UserPrefs()

    fun setRegion(region: String) { viewModelScope.launch { prefs.setRegion(region) } }
    fun setMinMag(min: Double) { viewModelScope.launch { prefs.setMinMag(min) } }
    fun setDarkMode(dark: Boolean) { viewModelScope.launch { prefs.setDarkMode(dark) } }
    fun setNotifications(enabled: Boolean) { viewModelScope.launch { prefs.setNotifications(enabled) } }
}


