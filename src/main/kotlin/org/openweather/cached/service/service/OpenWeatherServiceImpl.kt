package org.openweather.cached.service.service

import org.openweather.cached.service.connector.OpenWeatherConnector
import org.openweather.cached.service.gson.gson
import org.openweather.cached.service.model.Language
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.Units
import org.openweather.cached.service.model.interfaces.Location
import org.openweather.cached.service.model.response.OneCallResponse

class OpenWeatherServiceImpl(private val connector: OpenWeatherConnector, private val defaultUnits: Units) : OpenWeatherService {
    override suspend fun getOneCall(
        location: Location,
        exclude: Array<OneCallParts>,
        units: Units?,
        language: Language
    ): OneCallResponse {
        val rawResponse = connector.getOneCall(location, units ?: defaultUnits, exclude, language)
        return gson.fromJson(rawResponse, OneCallResponse::class.java)
    }
}
