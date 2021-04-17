package org.openweather.cached.service.model

interface Wind {
    /**
     * Wind speed. Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
     */
    val windSpeed: Float

    /**
     * Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
     */
    val windGust: Float?

    /**
     * Wind direction, degrees (meteorological)
     */
    val windDegree: Float
}
