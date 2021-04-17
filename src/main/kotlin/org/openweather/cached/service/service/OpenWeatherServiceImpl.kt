package org.openweather.cached.service.service

import org.openweather.cached.service.gson.gson
import org.openweather.cached.service.model.Location
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.OneCallResponse
import org.openweather.cached.service.model.Units
import org.openweather.cached.service.rest.OpenWeatherConnector

class OpenWeatherServiceImpl(private val connector: OpenWeatherConnector, private val defaultUnits: Units) : OpenWeatherService {
    override suspend fun getOneCall(location: Location, exclude: Array<OneCallParts>, units: Units?): OneCallResponse {
        val rawResponse = connector.getOneCall(location, units ?: defaultUnits, exclude)
        return gson.fromJson(rawResponse, OneCallResponse::class.java)
    }
}
