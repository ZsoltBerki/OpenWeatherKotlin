package org.openweather.cached.service.connector

import org.openweather.cached.service.model.Language
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.Units
import org.openweather.cached.service.model.interfaces.Location

interface OpenWeatherConnector {
    /**
     * Executes the "One Call API" for the given location.
     */
    suspend fun getOneCall(
        location: Location,
        units: Units = Units.METRIC,
        exclude: Array<OneCallParts> = emptyArray(),
        language: Language = Language.ENGLISH
    ): String
}
