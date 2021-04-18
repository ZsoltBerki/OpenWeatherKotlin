package org.openweather.cached.service.model.interfaces

interface Temperature {
    /**
     * Temperature.
     *
     * Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
     */
    val temperature: Float

    /**
     * Temperature. This accounts for the human perception of weather.
     *
     * Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
     */
    val feelsLike: Float
}
