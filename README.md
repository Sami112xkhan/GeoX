# GeoX – Real-time Disaster Intelligence System

GeoX: Android app for real-time earthquake and disaster insights

A modern Android app built with Kotlin and Jetpack Compose that provides real-time disaster intelligence using earthquake data from the USGS API and disaster data from NASA EONET.

## Features

- **Real-time Earthquake Data**: Fetches live earthquake data from USGS Earthquake API
- **NASA Disaster Data**: Integrates with NASA EONET for wildfires, storms, volcanoes, and other disasters
- **Interactive Map**: Custom map visualization with color-coded earthquake and disaster markers
- **Smart Notifications**: Local notifications for earthquakes and disasters above your threshold
- **Analytics Dashboard**: Charts showing earthquake trends and magnitude distribution
- **Event Details**: Detailed view for both earthquakes and disasters with sharing capabilities
- **Offline Support**: Room database caching for offline access
- **Dark Mode**: Full light/dark theme support
- **Settings**: Configurable region, magnitude threshold, and notification preferences

## Map Features

- **No API Key Required**: Uses a custom map visualization (MapLibre integration in progress)
- **Color-coded Earthquake Markers**: 
  - Green (small): Magnitude < 4.0
  - Orange (medium): Magnitude 4.0-6.0  
  - Red (large): Magnitude > 6.0
- **Disaster Markers**: Blue markers for NASA EONET disasters (wildfires, storms, volcanoes, etc.)
- **Interactive Details**: Tap markers to view detailed event information
- **Global View**: Shows both earthquake and disaster markers with category-based color coding

## Tech Stack

- **UI**: Jetpack Compose with Material 3
- **Maps**: Custom map visualization (MapLibre integration in progress)
- **Networking**: Retrofit + Moshi
- **Database**: Room for offline caching
- **Preferences**: DataStore
- **Charts**: MPAndroidChart
- **Architecture**: MVVM with Repository pattern

## Setup

1. Clone the repository
2. Open in Android Studio
3. Build and run - **No API keys required!**

## Map Data

This app currently uses a custom map visualization that displays earthquake data with color-coded markers. MapLibre GL integration is in progress to provide full OpenStreetMap-based mapping capabilities without requiring any API keys or billing setup.

## Permissions

The app requests the following permissions:
- `INTERNET` - For fetching earthquake data
- `ACCESS_NETWORK_STATE` - For network connectivity checks
- `ACCESS_FINE_LOCATION` - For user location (optional)
- `ACCESS_COARSE_LOCATION` - For approximate location (optional)
- `POST_NOTIFICATIONS` - For earthquake alerts

## Data Sources

### USGS Earthquake API
- **Base URL**: `https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/`
- **Daily feed**: `all_day.geojson` - earthquakes for past 24 hours
- **Real-time updates**: Every few minutes
- **Global coverage**: Magnitude, location, depth, and timing data
- **No API key required**

### NASA EONET API
- **Base URL**: `https://eonet.gsfc.nasa.gov/api/v3/events`
- **Disaster categories**: Wildfires, storms, volcanoes, floods, droughts, etc.
- **Real-time updates**: Active disaster events worldwide
- **Global coverage**: Location, category, description, and timing data
- **No API key required**

## Architecture

```
app/
├── ui/
│   ├── components/     # Reusable UI components (DisasterMap, FrostedCard, etc.)
│   ├── screens/        # Screen composables (Home, AlertDetail, Insights, Settings)
│   └── theme/          # Material 3 theme
├── data/
│   ├── model/          # Data models (EarthquakeEvent, DisasterEvent)
│   ├── network/        # Retrofit API services (USGS, NASA EONET)
│   ├── repository/     # Repository interfaces and implementations
│   ├── local/          # Room database entities and DAOs
│   └── prefs/          # DataStore preferences
├── viewmodel/          # ViewModels
└── di/                 # Dependency injection
```

## Building

```bash
./gradlew assembleDebug
```

## Installation

```bash
./gradlew installDebug
```

The app will install and run on your connected Android device without requiring any API keys or external setup.

## Repository Configuration

The app supports both real API data and fake data for offline demo:

- **Network Repositories**: `NetworkEarthquakeRepository` and `NetworkDisasterRepository` fetch live data from USGS and NASA EONET APIs
- **Fake Repository**: `FakeEarthquakeRepository` provides sample data for offline testing

To switch between real and fake data, modify the repository injection in `AppModule.kt`:

```kotlin
// For real API data
fun provideEarthquakeRepository(context: Context): EarthquakeRepository {
    return NetworkEarthquakeRepository(...)
}

// For fake data (offline demo)
fun provideEarthquakeRepository(context: Context): EarthquakeRepository {
    return FakeEarthquakeRepository()
}
```

## TODO: Future Enhancements

- [ ] Complete MapLibre GL integration for full mapping capabilities
- [ ] WorkManager for periodic data fetching and background updates
- [ ] Advanced filtering and search capabilities
- [ ] ML forecasting module for disaster prediction
- [ ] Push notifications for critical events
- [ ] Offline map tiles support
- [ ] Export functionality for event data
