package org.openweather.cached.service.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openweather.cached.service.configurations.OpenWeatherConfigurations
import org.openweather.cached.service.connector.OpenWeatherConnectorImpl
import org.openweather.cached.service.model.LocationImpl
import org.openweather.cached.service.model.Units

val clientMockData = HttpClient(MockEngine) {
    engine {
        addHandler { _ ->
            respond(
                """
                {
                   "lat":33.44,
                   "lon":-94.04,
                   "timezone":"America/Chicago",
                   "timezone_offset":-21600,
                   "current":{
                      "dt":1618317040,
                      "sunrise":1618282134,
                      "sunset":1618333901,
                      "temp":284.07,
                      "feels_like":282.84,
                      "pressure":1019,
                      "humidity":62,
                      "dew_point":277.08,
                      "uvi":0.89,
                      "clouds":0,
                      "visibility":10000,
                      "wind_speed":6,
                      "wind_deg":300,
                      "weather":[
                         {
                            "id":500,
                            "main":"Rain",
                            "description":"light rain",
                            "icon":"10d"
                         }
                      ],
                      "rain":{
                         "1h":0.21
                      }
                   },
                   "minutely":[
                      {
                         "dt":1618317060,
                         "precipitation":0.205
                      }
                   ],
                   "hourly":[
                      {
                         "dt":1618315200,
                         "temp":282.58,
                         "feels_like":280.4,
                         "pressure":1019,
                         "humidity":68,
                         "dew_point":276.98,
                         "uvi":1.4,
                         "clouds":19,
                         "visibility":306,
                         "wind_speed":4.12,
                         "wind_deg":296,
                         "wind_gust":7.33,
                         "weather":[
                            {
                               "id":801,
                               "main":"Clouds",
                               "description":"few clouds",
                               "icon":"02d"
                            }
                         ],
                         "pop":0
                      }
                   ],
                   "daily":[
                      {
                         "dt":1618308000,
                         "sunrise":1618282134,
                         "sunset":1618333901,
                         "moonrise":1618284960,
                         "moonset":1618339740,
                         "moon_phase":0.04,
                         "temp":{
                            "day":279.79,
                            "min":275.09,
                            "max":284.07,
                            "night":275.09,
                            "eve":279.21,
                            "morn":278.49
                         },
                         "feels_like":{
                            "day":277.59,
                            "night":276.27,
                            "eve":276.49,
                            "morn":276.27
                         },
                         "pressure":1020,
                         "humidity":81,
                         "dew_point":276.77,
                         "wind_speed":3.06,
                         "wind_deg":294,
                         "weather":[
                            {
                               "id":500,
                               "main":"Rain",
                               "description":"light rain",
                               "icon":"10d"
                            }
                         ],
                         "clouds":56,
                         "pop":0.2,
                         "rain":0.62,
                         "uvi":1.93
                      }
                   ],
                   "alerts":[
                      {
                         "sender_name":"NWS Tulsa",
                         "event":"Heat Advisory",
                         "start":1597341600,
                         "end":1597366800,
                         "description":"...HEAT ADVISORY REMAINS IN EFFECT FROM 1 PM THIS AFTERNOON TO\n8 PM CDT THIS EVENING...\n* WHAT...Heat index values of 105 to 109 degrees expected.\n* WHERE...Creek, Okfuskee, Okmulgee, McIntosh, Pittsburg,\nLatimer, Pushmataha, and Choctaw Counties.\n* WHEN...From 1 PM to 8 PM CDT Thursday.\n* IMPACTS...The combination of hot temperatures and high\nhumidity will combine to create a dangerous situation in which\nheat illnesses are possible."
                      }
                   ]
                }
                """.trimIndent()
            )
        }
    }
}

val configurations = OpenWeatherConfigurations(
    baseUrl = "http://api.openweathermap.org",
    version = "6.6",
    apiKey = "1234abcd"
)

val connector = OpenWeatherConnectorImpl(configurations, clientMockData)
val service = OpenWeatherServiceImpl(connector, Units.METRIC)
val location = LocationImpl(33.44F, -94.04F)

class OpenWeatherServiceTest {
    @Test
    fun `Validate generic one-call response - core`() {
        val response = runBlocking {
            service.getOneCall(location)
        }

        assertEquals(33.44F, response.latitude)
        assertEquals(-94.04F, response.longitude)
        assertEquals("America/Chicago", response.timezoneName)
        assertEquals(-21600L, response.timezoneOffset)
    }

    @Test
    fun `Validate generic one-call response - current`() {
        val response = runBlocking {
            service.getOneCall(location)
        }
        assertEquals(1618317040L, response.current?.dateTime?.toEpochSecond())
        assertEquals(1618282134L, response.current?.sunrise?.toEpochSecond())
        assertEquals(1618333901L, response.current?.sunset?.toEpochSecond())
        assertEquals(284.07F, response.current?.temperature)
        assertEquals(282.84F, response.current?.feelsLike)
        assertEquals(1019, response.current?.pressure)
        assertEquals(62, response.current?.humidity)
        assertEquals(277.08F, response.current?.dewPoint)
        assertEquals(0.89F, response.current?.uvi)
        assertEquals(0, response.current?.cloudiness)
        assertEquals(10000, response.current?.visibility)
        assertEquals(300F, response.current?.windDegree)
        assertEquals(6F, response.current?.windSpeed)
        assertEquals(null, response.current?.windGust)
        assertEquals(1, response.current?.weatherMetadata?.size)
        assertEquals(500, response.current?.weatherMetadata?.get(0)?.id)
        assertEquals("Rain", response.current?.weatherMetadata?.get(0)?.name)
        assertEquals("light rain", response.current?.weatherMetadata?.get(0)?.description)
        assertEquals("10d", response.current?.weatherMetadata?.get(0)?.icon)
        assertEquals(null, response.current?.snowVolume)
        assertEquals(0.21F, response.current?.rainVolume?.lastHour)
    }

    @Test
    fun `Validate generic one-call response - hourly`() {
        val response = runBlocking {
            service.getOneCall(location)
        }
        assertEquals(1, response.hourly?.size)

        val firstHour = response.hourly?.get(0)

        assertEquals(1618315200L, firstHour?.dateTime?.toEpochSecond())
        assertEquals(282.58F, firstHour?.temperature)
        assertEquals(280.4F, firstHour?.feelsLike)
        assertEquals(1019, firstHour?.pressure)
        assertEquals(68, firstHour?.humidity)
        assertEquals(276.98F, firstHour?.dewPoint)
        assertEquals(1.4F, firstHour?.uvi)
        assertEquals(19, firstHour?.cloudiness)
        assertEquals(306, firstHour?.visibility)
        assertEquals(296F, firstHour?.windDegree)
        assertEquals(4.12F, firstHour?.windSpeed)
        assertEquals(7.33F, firstHour?.windGust)
        assertEquals(1, firstHour?.weatherMetadata?.size)
        assertEquals(801, firstHour?.weatherMetadata?.get(0)?.id)
        assertEquals("Clouds", firstHour?.weatherMetadata?.get(0)?.name)
        assertEquals("few clouds", firstHour?.weatherMetadata?.get(0)?.description)
        assertEquals("02d", firstHour?.weatherMetadata?.get(0)?.icon)
        assertEquals(null, firstHour?.snowVolume)
        assertEquals(null, firstHour?.rainVolume)
        assertEquals(0.0F, firstHour?.precipitationProbability)
    }

    @Test
    fun `Validate generic one-call response - daily`() {
        val response = runBlocking {
            service.getOneCall(location)
        }

        assertEquals(1, response.daily?.size)

        val firstDay = response.daily?.get(0)

        assertEquals(1618308000L, firstDay?.dateTime?.toEpochSecond())
        assertEquals(1618282134L, firstDay?.sunrise?.toEpochSecond())
        assertEquals(1618333901L, firstDay?.sunset?.toEpochSecond())
        assertEquals(1618284960L, firstDay?.moonRise?.toEpochSecond())
        assertEquals(1618339740L, firstDay?.moonSet?.toEpochSecond())
        assertEquals(0.04F, firstDay?.moonPhase)

        assertEquals(278.49F, firstDay?.dailyTemperature?.morning)
        assertEquals(279.79F, firstDay?.dailyTemperature?.day)
        assertEquals(279.21F, firstDay?.dailyTemperature?.evening)
        assertEquals(275.09F, firstDay?.dailyTemperature?.night)
        assertEquals(275.09F, firstDay?.dailyTemperature?.min)
        assertEquals(284.07F, firstDay?.dailyTemperature?.max)

        assertEquals(276.27F, firstDay?.dailyFeelsLike?.morning)
        assertEquals(277.59F, firstDay?.dailyFeelsLike?.day)
        assertEquals(276.49F, firstDay?.dailyFeelsLike?.evening)
        assertEquals(276.27F, firstDay?.dailyFeelsLike?.night)

        assertEquals(1020, firstDay?.pressure)
        assertEquals(81, firstDay?.humidity)
        assertEquals(276.77F, firstDay?.dewPoint)
        assertEquals(1.93F, firstDay?.uvi)
        assertEquals(56, firstDay?.cloudiness)
        assertEquals(0.2F, firstDay?.precipitationProbability)
        assertEquals(294F, firstDay?.windDegree)
        assertEquals(3.06F, firstDay?.windSpeed)
        assertEquals(null, firstDay?.windGust)
        assertEquals(1, firstDay?.weatherMetadata?.size)
        assertEquals(500, firstDay?.weatherMetadata?.get(0)?.id)
        assertEquals("Rain", firstDay?.weatherMetadata?.get(0)?.name)
        assertEquals("light rain", firstDay?.weatherMetadata?.get(0)?.description)
        assertEquals("10d", firstDay?.weatherMetadata?.get(0)?.icon)
        assertEquals(null, firstDay?.snowVolume)
        assertEquals(0.62F, firstDay?.rainVolume)
    }

    @Test
    fun `Validate generic one-call response - minutely`() {
        val response = runBlocking {
            service.getOneCall(location)
        }

        assertEquals(1, response.minutely?.size)

        val firstMinute = response.minutely?.get(0)

        assertEquals(1618317060L, firstMinute?.dateTime?.toEpochSecond())
        assertEquals(0.205F, firstMinute?.precipitation)
    }

    @Test
    fun `Validate generic one-call response - alerts`() {
        val response = runBlocking {
            service.getOneCall(location)
        }

        assertEquals(1, response.alerts?.size)

        val firstAlert = response.alerts?.get(0)

        assertEquals(1597341600L, firstAlert?.start?.toEpochSecond())
        assertEquals(1597366800L, firstAlert?.end?.toEpochSecond())
        assertEquals("NWS Tulsa", firstAlert?.sender)
        assertEquals("Heat Advisory", firstAlert?.event)
        assertEquals(
            """
            ...HEAT ADVISORY REMAINS IN EFFECT FROM 1 PM THIS AFTERNOON TO\n8 PM CDT THIS EVENING...\n* WHAT...Heat index values of 105 to 109 degrees expected.\n* WHERE...Creek, Okfuskee, Okmulgee, McIntosh, Pittsburg,\nLatimer, Pushmataha, and Choctaw Counties.\n* WHEN...From 1 PM to 8 PM CDT Thursday.\n* IMPACTS...The combination of hot temperatures and high\nhumidity will combine to create a dangerous situation in which\nheat illnesses are possible.
            """.trimIndent().replace("\\n", System.getProperty("line.separator")),
            firstAlert?.description
        )
    }
}
