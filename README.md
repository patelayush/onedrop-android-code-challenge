### Goal: Weather Forecasting for New York

Using [OpenWeather's REST API](https://openweathermap.org/api) to build an Android application that displays the current weather in your location.

### Code Walkthrough
1. This application is implemented in Kotlin. I have used MVP architecture style to make unit testing easier and to increase simplicity and reusability of code.
2. When the app is launched, it checks for the location permission.
  a. If provided, then shows the weather of current location.
  b. If not provided, then shows a dialog asking users to grant permission. User is taken to permission settings, when clicked on okay. User can come back to the app after granting location permission and try to fetch weather again.
3. Main screen shows information regarding weather -
  * Location (i.e. New York, etc)
  * Temperature
  * Humidity
  * Feels like
  * Wind speed/degrees
  * Pressure
4. When a user clicks anywhere on the screen, we show the user 7-day forecast weather in details screen. Details screen contains information such as date, temperature range and short description.
5. User can come back to the main screen anytime using back button functionality.

## Note to Testers
1. At present, "one call api" is returning same date for next 8 days forecast in "daily" object. That's the reason you will find same date in details screen.
2. Also, api doen't return location object, thus I am using GeoDecoder(Android library) to fetch the location name from latitude and longitude.

## Future Work -
1. Implementing unit test for verifying network calls.
2. Beautifying layout with more details to show.
3. Using location listener, instead of getLastKnownLocation, to update the user's location more precisely and fetch the lat and long from that.

### Screenshots

![Alt text](/screenshots/first.png?raw=true "Main Screen")
![Alt text](/screenshots/second.png?raw=true "Details screen")
