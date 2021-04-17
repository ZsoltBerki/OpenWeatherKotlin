package org.openweather.cached.service.rest

import org.openweather.cached.service.model.Location
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.Units

interface OpenWeatherConnector {
    /**
     * Executes the "One Call API" for the given location.
     */
    // TODO support language parameter
    suspend fun getOneCall(
        location: Location,
        units: Units = Units.METRIC,
        exclude: Array<OneCallParts> = emptyArray()
    ): String
}
