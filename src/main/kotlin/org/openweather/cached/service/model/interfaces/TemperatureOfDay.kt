package org.openweather.cached.service.model.interfaces

interface TemperatureOfDay {
    /**
     * Morning temperature.
     */
    val morning: Float

    /**
     * Day temperature.
     */
    val day: Float

    /**
     * Evening temperature.
     */
    val evening: Float

    /**
     * Night temperature.
     */
    val night: Float
}
