package org.openweather.cached.service.model

import org.openweather.cached.service.model.interfaces.Location

data class LocationImpl(
    override val latitude: Float,
    override val longitude: Float,
) : Location
