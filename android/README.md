# GeoX Android App

This is the Android Kotlin version of the GeoX disaster intelligence dashboard app, targeting Android 15 (API level 35) and above.

## Features

- **Splash Screen**: Animated glass morphism sphere with gradient background
- **Home Screen**: Interactive map view with disaster pins, searchable disaster cards, and bottom sheet
- **Insights Screen**: Analytics and charts for disaster data visualization
- **Filters Screen**: Customizable filters for disaster types, magnitude, and radius
- **Settings Screen**: App preferences and data source information
- **About Screen**: App information and mission statement
- **Alert Detail Modal**: Detailed view of disaster information with AI predictions

## Project Structure

```
android/
├── app/
│   ├── src/main/
│   │   ├── java/com/geox/app/
│   │   │   ├── data/              # Data models
│   │   │   ├── theme/             # Theme, colors, typography
│   │   │   ├── ui/
│   │   │   │   ├── components/    # Reusable UI components
│   │   │   │   └── screens/       # Screen composables
│   │   │   └── MainActivity.kt   # Main entry point
│   │   └── res/
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## Building the Project

1. Open the project in Android Studio (Hedgehog or later recommended)
2. Sync Gradle files
3. Ensure you have Android SDK 35 installed
4. Build and run on an emulator or device running Android 15+

## Requirements

- **Minimum SDK**: 35 (Android 15)
- **Target SDK**: 35 (Android 15)
- **Kotlin**: 1.9.22+
- **Jetpack Compose**: 2024.01.00+
- **Gradle**: 8.3.0+

## Key Technologies

- **Jetpack Compose**: Modern declarative UI framework
- **Material 3**: Latest Material Design components
- **Kotlin Coroutines**: For asynchronous operations
- **Animation API**: For smooth transitions and effects

## Design System

The app uses a glass morphism design language with:
- Liquid glass effects using backdrop blur and gradients
- Lime green (#C4FF0D) as the primary accent color
- Premium shadows and depth effects
- Smooth animations throughout

## Notes

- The app currently uses mock data. Replace `MockData.kt` with actual API integration as needed.
- Chart visualization in InsightsScreen uses placeholder bars. You can integrate a chart library like Vico or MPAndroidChart if needed.
- The map view uses a simplified visual representation. Consider integrating Google Maps or similar for production use.

## License

Same as the original React app.

