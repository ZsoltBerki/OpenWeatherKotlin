package org.openweather.cached.service.service

import org.openweather.cached.service.model.Language
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.Units
import org.openweather.cached.service.model.interfaces.Location
import org.openweather.cached.service.model.response.OneCallResponse

interface OpenWeatherService {
    /**
     * Executes the "One Call API" for the given location.
     */
    suspend fun getOneCall(
        location: Location,
        exclude: Array<OneCallParts> = emptyArray(),
        units: Units? = null,
        language: Language = Language.ENGLISH
    ): OneCallResponse
}
