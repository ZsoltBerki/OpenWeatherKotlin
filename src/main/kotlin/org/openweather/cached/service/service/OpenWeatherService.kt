package org.openweather.cached.service.service

import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.OneCallResponse
import org.openweather.cached.service.model.Units
import org.openweather.cached.service.model.interfaces.Location

interface OpenWeatherService {
    /**
     * Executes the "One Call API" for the given location.
     */
    // TODO support language parameter
    suspend fun getOneCall(
        location: Location,
        exclude: Array<OneCallParts> = emptyArray(),
        units: Units? = null
    ): OneCallResponse
}
