package org.openweather.cached.service.model

data class LocationImpl(
    override val latitude: Float,
    override val longitude: Float,
) : Location
