package org.openweather.cached.service.model.interfaces

interface WeatherConditions {
    /**
     * Atmospheric pressure on the sea level, hPa
     */
    val pressure: Int

    /**
     * Humidity, %
     */
    val humidity: Byte

    /**
     * Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin
     * to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
     */
    val dewPoint: Float

    /**
     * Cloudiness, %
     */
    val cloudiness: Byte

    /**
     * Current UV index
     */
    val uvi: Float
}
