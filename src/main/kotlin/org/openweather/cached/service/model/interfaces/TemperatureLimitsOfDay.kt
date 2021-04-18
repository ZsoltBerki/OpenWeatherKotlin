package org.openweather.cached.service.model.interfaces

interface TemperatureLimitsOfDay {
    /**
     * Min daily temperature.
     */
    val min: Float

    /**
     * Max daily temperature.
     */
    val max: Float
}
