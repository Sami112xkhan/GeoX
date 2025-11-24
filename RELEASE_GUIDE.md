# GeoX Release Guide

## Building a Release APK

### Version: 0.1.0

This guide provides instructions for building a release APK for the GeoX app.

---

## Quick Command

For building an unsigned release APK (suitable for testing):

```bash
cd android
./gradlew assembleRelease
```

The APK will be generated at:
```
android/app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## Building a Signed Release APK

For publishing to Google Play Store or distributing to users, you need a signed APK.

### Step 1: Generate a Keystore (First Time Only)

```bash
keytool -genkey -v -keystore geox-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias geox-release
```

**Important:** Save your keystore password and key password securely. You'll need them for future releases.

### Step 2: Configure Signing in gradle.properties

Create or edit `android/gradle.properties` (add to .gitignore):

```properties
GEOX_RELEASE_STORE_FILE=../geox-release-key.jks
GEOX_RELEASE_STORE_PASSWORD=your_keystore_password
GEOX_RELEASE_KEY_ALIAS=geox-release
GEOX_RELEASE_KEY_PASSWORD=your_key_password
```

### Step 3: Update build.gradle.kts

The signing configuration needs to be added to `android/app/build.gradle.kts` in the `android` block:

```kotlin
signingConfigs {
    create("release") {
        storeFile = file(project.properties["GEOX_RELEASE_STORE_FILE"] as String? ?: "")
        storePassword = project.properties["GEOX_RELEASE_STORE_PASSWORD"] as String? ?: ""
        keyAlias = project.properties["GEOX_RELEASE_KEY_ALIAS"] as String? ?: ""
        keyPassword = project.properties["GEOX_RELEASE_KEY_PASSWORD"] as String? ?: ""
    }
}

buildTypes {
    release {
        signingConfig = signingConfigs.getByName("release")
        isMinifyEnabled = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}
```

### Step 4: Build Signed Release APK

```bash
cd android
./gradlew assembleRelease
```

The signed APK will be at:
```
android/app/build/outputs/apk/release/app-release.apk
```

---

## Alternative: Build AAB for Google Play Store

Google Play Store prefers Android App Bundles (AAB):

```bash
cd android
./gradlew bundleRelease
```

The AAB will be at:
```
android/app/build/outputs/bundle/release/app-release.aab
```

---

## Release Information

### Release Title
**GeoX v0.1.0 - Real-time Disaster Intelligence System**

### Release Notes

**GeoX v0.1.0 - Initial Release** 🌍⚡

We're excited to announce the first public release of GeoX - your real-time disaster intelligence companion!

#### 🎉 Key Features

**Real-time Data Integration**
- Live earthquake data from USGS Earthquake API
- NASA EONET disaster data (wildfires, storms, volcanoes, floods)
- Real-time updates every few minutes
- No API keys required

**Interactive Map Experience**
- Custom map visualization using OSMDroid with OpenStreetMap
- Color-coded markers for different disaster types:
  - 🔴 Red: Earthquakes
  - 🟠 Orange: Wildfires
  - 🟣 Purple: Volcanoes
  - 🔵 Blue: Floods
  - 🔷 Cyan: Storms
- Google Maps-style location marker with pulse animation
- Tap markers to view detailed event information

**Material 3 Modern UI**
- Beautiful Material 3 expressive design
- Full dark/light theme support
- Smooth animations and transitions
- Android 16-style dynamic colors

**Smart Features**
- Analytics dashboard with earthquake trends and magnitude distribution
- Location-based filtering by distance
- Detailed event views with sharing capabilities
- Offline support with Room database caching
- Configurable settings (region, magnitude threshold, notifications)

**Privacy & Permissions**
- Internet access for data fetching
- Optional location permissions for nearby disaster alerts
- Local notifications for earthquakes above your threshold

#### 🏗️ Tech Stack
- Kotlin with Jetpack Compose
- Material 3 UI components
- OSMDroid for mapping
- Retrofit for networking
- Room for offline caching
- MPAndroidChart for analytics
- MVVM architecture with Repository pattern

#### 📱 Requirements
- Android 8.0 (API 26) or higher
- Internet connection for live data
- Optional: Location permissions for nearby alerts

#### 🚀 Getting Started
1. Install the app
2. Grant location permissions (optional)
3. Explore real-time disasters on the interactive map
4. Configure your preferences in Settings
5. Stay informed about disasters worldwide

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

## Installation Commands

Install the APK on a connected device:

```bash
cd android
adb install app/build/outputs/apk/release/app-release.apk
```

Or for unsigned APK:

```bash
cd android
adb install app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## Testing the Release Build

Before publishing:

1. **Test on multiple devices**: Test on different Android versions (API 26+)
2. **Check all features**: Verify map, data fetching, notifications, settings
3. **Test offline mode**: Disable internet and check offline functionality
4. **Verify permissions**: Ensure location permissions work correctly
5. **Check ProGuard**: Ensure code minification doesn't break functionality

---

## Version Information

- **Version Name**: 0.1.0
- **Version Code**: 1
- **Package Name**: com.geox.app
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35 (Android 15)
- **Compile SDK**: 35

---

## Troubleshooting

### Build Fails
- Ensure you're using JDK 17
- Run `./gradlew clean` before building
- Check for Gradle sync issues in Android Studio

### Keystore Issues
- Verify keystore file path in gradle.properties
- Ensure passwords are correct
- Check that keystore file exists

### Installation Issues
- Enable "Install from Unknown Sources" in device settings
- Check device compatibility (Android 8.0+)
- Verify sufficient storage space

---

## Security Notes

⚠️ **Important:**
- Never commit your keystore file to version control
- Never commit gradle.properties files containing passwords or sensitive credentials
- Add to .gitignore (if using local gradle.properties for signing):
  ```
  *.jks
  *.keystore
  android/local.properties
  # Only add if you store credentials in gradle.properties
  # android/gradle.properties
  ```
- Alternative: Use environment variables or a secure key management system for CI/CD
- Store keystore and passwords securely for future releases

---

For more information, see the main [README.md](README.md).
