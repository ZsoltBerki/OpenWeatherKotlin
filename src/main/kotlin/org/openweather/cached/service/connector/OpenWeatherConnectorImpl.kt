package org.openweather.cached.service.connector

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.openweather.cached.service.configurations.OpenWeatherConfigurations
import org.openweather.cached.service.model.Language
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.Units
import org.openweather.cached.service.model.interfaces.Location

class OpenWeatherConnectorImpl(private val config: OpenWeatherConfigurations) : OpenWeatherConnector {
    companion object {
        private const val PARAMETER_LATITUDE = "lat"
        private const val PARAMETER_LONGITUDE = "lon"
        private const val PARAMETER_API_KEY = "appid"
        private const val PARAMETER_UNITS = "units"
        private const val PARAMETER_EXCLUDE = "exclude"
        private const val PARAMETER_LANGUAGE = "lang"
    }

    private fun getClient(): HttpClient {
        return HttpClient(CIO)
    }

    override suspend fun getOneCall(
        location: Location,
        units: Units,
        exclude: Array<OneCallParts>,
        language: Language
    ): String {
        validateLocation(location)

        val url = "${config.baseUrl}/data/${config.version}/onecall"

        val excludedParts = when (exclude.isNotEmpty()) {
            true -> exclude.map { it.fieldName }.reduceRight { a, b -> "$a,$b" }
            false -> null
        }

        return getClient().use { client ->
            client.get<String>(url) {
                parameter(PARAMETER_LATITUDE, location.latitude)
                parameter(PARAMETER_LONGITUDE, location.longitude)
                parameter(PARAMETER_API_KEY, config.apiKey)
                parameter(PARAMETER_UNITS, units)
                parameter(PARAMETER_LANGUAGE, language.id)
                if (excludedParts != null) {
                    parameter(PARAMETER_EXCLUDE, excludedParts)
                }
            }
        }
    }
}
