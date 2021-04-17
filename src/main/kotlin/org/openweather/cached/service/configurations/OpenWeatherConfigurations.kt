package org.openweather.cached.service.configurations

data class OpenWeatherConfigurations(
    val baseUrl: String = "http://api.openweathermap.org",
    val version: String = "2.5",
    val apiKey: String
)
