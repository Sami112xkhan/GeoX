# GeoX v0.2.0 - Real Data Upgrade

## 🚀 Highlights
- **Dark Mode Everywhere**: Settings toggle now switches the entire app theme using Material 3 dynamic colors.
- **Insight Overhaul**: Added live metrics (strongest quake, 24h activity, average depth, total tracked events), significant-event list, and real category breakdown bars.
- **Detail Modal Polish**: Smaller embedded map plus working Source/Save/Share actions and non-blocking layout.
- **Home Map Improvements**: Responsive overlays, smoother user-location lock, and nearest-distance badge clarity.

## 📦 Assets
- `GeoX-v0.2.0.apk` — unsigned release build for sideload/testing.

## ✅ Fixes
- Map overlays no longer lag or hide buttons.
- Text colors now adapt automatically in dark mode.
- Insights page now shows real ComCat/EONET data instead of placeholders.

## 🔜 Next
- Signed builds
- Push notifications for nearby disasters
- Offline map tiles & caching

---

# GeoX v0.1.0 - Initial Release

## 🎉 First Public Release

GeoX is now available! A modern Android app for real-time disaster intelligence.

## ✨ Features

### Core Functionality
- **Real-time Disaster Data**: Live earthquake data from USGS and disaster events from NASA EONET
- **Interactive Map**: OSMDroid-based map with clickable disaster markers
- **Material 3 UI**: Beautiful, expressive interface following Android 16 design guidelines
- **Location Services**: Google Maps-style location marker with automatic centering
- **Smart Filtering**: Filter disasters by type, magnitude, and distance from your location

### Map Features
- 🗺️ **No API Key Required**: Uses OpenStreetMap tiles via OSMDroid
- 🎯 **Color-coded Markers**: 
  - 🔴 Red: Earthquakes
  - 🟠 Orange: Wildfires
  - 🟣 Purple: Volcanoes
  - 🔵 Blue: Floods
  - 🔷 Cyan: Storms
- 👆 **Interactive**: Tap any marker to view detailed disaster information
- 📍 **User Location**: Beautiful location marker with pulse animation
- 🎯 **Auto-centering**: Automatically shows nearest disasters when location is available

### Analytics & Insights
- 📊 **Earthquake Frequency Chart**: 30-day trend visualization
- 📈 **Magnitude Distribution**: Bar chart showing earthquake magnitudes
- 📱 **Real-time Updates**: Data refreshes automatically from APIs

### User Experience
- 🔔 **Notifications**: View recent alerts near you
- 🔍 **Search**: Search disasters by name or location
- 💾 **Offline Support**: Room database caching for offline access
- 🌙 **Dark Mode**: Full theme support (coming soon)
- ⚙️ **Settings**: Configure refresh rate, clear cache, and more

### Detail View
- 📋 **Comprehensive Information**: Location, magnitude, time, and description
- 🗺️ **Map Preview**: See disaster location on embedded map
- 🔗 **Source Button**: Open original data source (USGS/NASA)
- 💾 **Save Button**: Save disasters to favorites
- 📤 **Share Button**: Share disaster information with others

## 🛠️ Technical Highlights

- **Architecture**: MVVM with Repository pattern
- **UI Framework**: Jetpack Compose with Material 3
- **Maps**: OSMDroid with OpenStreetMap
- **Networking**: Retrofit + Moshi for API calls
- **Database**: Room for local caching
- **Preferences**: DataStore for settings persistence
- **Charts**: MPAndroidChart for analytics
- **Location**: Google Play Services Location API

## 📱 Installation

1. Download `GeoX-v0.1.0.apk`
2. Enable "Install from Unknown Sources" on your Android device
3. Install the APK
4. Grant location permissions when prompted (optional but recommended)

## 🔐 Permissions

- `INTERNET`: Required for fetching disaster data
- `ACCESS_NETWORK_STATE`: Network connectivity checks
- `ACCESS_FINE_LOCATION`: Optional - for location-based filtering
- `ACCESS_COARSE_LOCATION`: Optional - for approximate location

## 📊 Data Sources

- **USGS Earthquake API**: Real-time global earthquake data
- **NASA EONET API**: Active disaster events worldwide
- **No API keys required** - All data sources are public and free

## 🐛 Known Issues

- Release APK is unsigned (for testing purposes)
- Some UI elements may need refinement
- Dark mode toggle in settings (UI only, full theme coming soon)

## 🚀 What's Next

- Signed release builds
- Push notifications for critical events
- Enhanced map features
- Offline map tiles
- Export functionality
- ML-based disaster prediction

## 📝 Notes

This is an unsigned APK for testing. For production use, please sign the APK with your own keystore.

---

**Version**: 0.1.0  
**Build Date**: November 2024  
**Min SDK**: 26 (Android 8.0)  
**Target SDK**: 35 (Android 15)

