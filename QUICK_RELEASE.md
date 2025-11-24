# Quick Release Commands for GeoX

## 🚀 Build Release APK Command

```bash
cd android
./gradlew assembleRelease
```

**Output Location:**
```
android/app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## 📦 Build Release AAB (For Google Play Store)

```bash
cd android
./gradlew bundleRelease
```

**Output Location:**
```
android/app/build/outputs/bundle/release/app-release.aab
```

---

## 📋 Release Title

**GeoX v0.1.0 - Real-time Disaster Intelligence System**

---

## 📝 Release Notes

### GeoX v0.1.0 - Initial Release 🌍⚡

We're excited to announce the first public release of GeoX - your real-time disaster intelligence companion!

#### ✨ What's New

**Real-time Data**
- Live earthquake data from USGS Earthquake API
- NASA EONET disaster data (wildfires, storms, volcanoes, floods)
- Automatic updates every few minutes
- Works without API keys

**Interactive Map**
- Beautiful OpenStreetMap integration with OSMDroid
- Color-coded disaster markers (🔴 Earthquakes, 🟠 Wildfires, 🟣 Volcanoes, 🔵 Floods, 🔷 Storms)
- Tap markers for detailed information
- Google Maps-style location marker with pulse animation

**Modern UI**
- Material 3 expressive design with dynamic colors
- Full dark/light theme support
- Smooth animations and transitions
- Android 16-style UI patterns

**Smart Features**
- Analytics dashboard with earthquake trends and charts
- Location-based filtering by distance
- Smart notifications for earthquakes above your threshold
- Offline support with local database caching
- Event sharing and source links
- Configurable settings (region, magnitude threshold, preferences)

#### 🛠️ Technical Details

**Tech Stack**
- Kotlin with Jetpack Compose
- Material 3 UI components
- OSMDroid for mapping
- Retrofit for networking
- Room for offline storage
- MPAndroidChart for data visualization
- MVVM architecture with Repository pattern

**Requirements**
- Android 8.0 (API 26) or higher
- Internet connection for live data
- Optional: Location permissions for nearby alerts

#### 🎯 Getting Started
1. Install the app on your Android device
2. Grant location permissions (optional)
3. Explore real-time disasters on the interactive map
4. Configure your preferences in Settings
5. Stay informed about disasters worldwide

#### 📱 Permissions
- `INTERNET` - For fetching earthquake and disaster data
- `ACCESS_NETWORK_STATE` - For network connectivity checks
- `ACCESS_FINE_LOCATION` - For user location (optional)
- `ACCESS_COARSE_LOCATION` - For approximate location (optional)
- `POST_NOTIFICATIONS` - For earthquake alerts (optional)

#### 🌐 Data Sources
- **USGS Earthquake API**: earthquake.usgs.gov (free, no API key required)
- **NASA EONET API**: eonet.gsfc.nasa.gov (free, no API key required)

#### 🔮 Coming Soon
- WorkManager for background updates
- Advanced filtering and search
- ML-based disaster prediction
- Push notifications for critical events
- Offline map tiles support
- Data export functionality

---

**Download GeoX v0.1.0 and stay informed about disasters in real-time!**

For issues, feedback, or contributions, visit our [GitHub repository](https://github.com/Sami112xkhan/GeoX).

---

## 📥 Installation

Install on a connected device:

```bash
cd android
adb install app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## 📚 Additional Information

- **Version Name**: 0.1.0
- **Version Code**: 1
- **Package Name**: com.geox.app
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35 (Android 15)

---

See [RELEASE_GUIDE.md](RELEASE_GUIDE.md) for detailed build instructions including signing configuration.
See [RELEASE_NOTES.md](RELEASE_NOTES.md) for complete release notes with all features and technical details.
