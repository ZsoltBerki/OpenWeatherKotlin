package org.openweather.cached.service.model

/**
 * Defines the units used for the returned data.
 *
 * Temperature is available in Fahrenheit, Celsius and Kelvin units.
 * Wind speed is available in miles/hour and meter/sec.
 */
enum class Units(val fieldName: String) {
    /**
     * Temperature is in Kelvin and wind speed is in meter/sec
     */
    STANDARD("standard"),
    /**
     * Temperature is in Celsius and wind speed is in meter/sec
     */
    METRIC("metric"),
    /**
     * Temperature is in Fahrenheit and wind speed is in miles/hour
     */
    IMPERIAL("imperial")
}
