package org.openweather.cached.service.service

import org.openweather.cached.service.model.Location
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.OneCallResponse
import org.openweather.cached.service.model.Units

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
