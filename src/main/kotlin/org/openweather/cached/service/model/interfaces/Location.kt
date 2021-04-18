package org.openweather.cached.service.model.interfaces

interface Location {
    /**
     * Geographical coordinates of the location (latitude)
     */
    val latitude: Float

    /**
     * Geographical coordinates of the location (longitude)
     */
    val longitude: Float
}
