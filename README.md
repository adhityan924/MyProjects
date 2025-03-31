# Farmer Hub Android App

A comprehensive Android application designed to help farmers manage their agricultural activities efficiently. The app provides real-time weather information, market prices for crops, and crop management features.

## Features

- **Weather Information**
  - Real-time temperature, humidity, and wind speed
  - Weather description and conditions
  - Location-based weather data

- **Market Prices**
  - Current market prices for various crops
  - Price trends and updates
  - Easy-to-read price display

- **Crop Management**
  - Add and track crops
  - Record crop details (name, variety, area)
  - Store notes and observations

## Technical Details

- Built with Android Studio
- Uses Room Database for local storage
- Implements MVVM architecture
- Material Design UI components
- Retrofit for API integration

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Add your OpenWeatherMap API key in `MainActivity.java`
4. Update the market prices API endpoint in `MainActivity.java`
5. Build and run the application

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34
- Internet permission for API calls
