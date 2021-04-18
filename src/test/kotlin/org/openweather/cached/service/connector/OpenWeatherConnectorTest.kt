package org.openweather.cached.service.connector

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.openweather.cached.service.configurations.OpenWeatherConfigurations
import org.openweather.cached.service.model.Language
import org.openweather.cached.service.model.LocationImpl
import org.openweather.cached.service.model.OneCallParts
import org.openweather.cached.service.model.Units

val clientUrlMirror = HttpClient(MockEngine) {
    engine {
        addHandler { request ->
            respond(request.url.toString())
        }
    }
}

val configurations = OpenWeatherConfigurations(
    baseUrl = "http://api.openweathermap.org",
    version = "6.6",
    apiKey = "1234abcd"
)

val connector = OpenWeatherConnectorImpl(configurations, clientUrlMirror)
val location = LocationImpl(23.123F, 32.321F)

class OpenWeatherConnectorTest {
    @Test
    fun `OneCall - URL - default`() {
        val expected = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=metric&lang=en
        """.trimIndent()

        val result = runBlocking {
            connector.getOneCall(location)
        }
        assertEquals(expected, result)
    }

    @Test
    fun `OneCall - URL - units`() {
        val expected_METRIC = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=metric&lang=en
        """.trimIndent()
        val expected_STANDARD = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=standard&lang=en
        """.trimIndent()
        val expected_IMPERIAL = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=imperial&lang=en
        """.trimIndent()

        val result_METRIC = runBlocking {
            connector.getOneCall(location, units = Units.METRIC)
        }
        val result_STANDARD = runBlocking {
            connector.getOneCall(location, units = Units.STANDARD)
        }
        val result_IMPERIAL = runBlocking {
            connector.getOneCall(location, units = Units.IMPERIAL)
        }
        assertEquals(expected_METRIC, result_METRIC)
        assertEquals(expected_STANDARD, result_STANDARD)
        assertEquals(expected_IMPERIAL, result_IMPERIAL)
    }

    @Test
    fun `OneCall - URL - excludes`() {
        val result_one = runBlocking {
            connector.getOneCall(location, exclude = listOf(OneCallParts.CURRENT))
        }
        val result_more = runBlocking {
            connector.getOneCall(
                location,
                exclude = listOf(
                    OneCallParts.CURRENT,
                    OneCallParts.MINUTELY,
                    OneCallParts.HOURLY,
                    OneCallParts.DAILY,
                    OneCallParts.ALERTS
                )
            )
        }

        val expected_one = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=metric&lang=en&exclude=current
        """.trimIndent()

        val expected_more = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=metric&lang=en&exclude=current%2Cminutely%2Chourly%2Cdaily%2Calerts
        """.trimIndent()

        assertEquals(expected_one, result_one)
        assertEquals(expected_more, result_more)
    }

    @Test
    fun `OneCall - URL - languages`() {
        val expected_fi = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=metric&lang=fi
        """.trimIndent()

        val expected_hu = """
            http://api.openweathermap.org/data/6.6/onecall?lat=23.123&lon=32.321&appid=1234abcd&units=metric&lang=hu
        """.trimIndent()

        val result_fi = runBlocking {
            connector.getOneCall(location, language = Language.FINNISH)
        }

        val result_hu = runBlocking {
            connector.getOneCall(location, language = Language.HUNGARIAN)
        }

        assertEquals(expected_fi, result_fi)
        assertEquals(expected_hu, result_hu)
    }

    @Test
    fun `OneCall - URL - wrong coordinates`() {
        val exception_lowLatitue = assertThrows<InvalidLatitude>("Should throw InvalidLatitude") {
            runBlocking {
                connector.getOneCall(LocationImpl(latitude = -91F, longitude = 0F))
            }
        }
        assertEquals("-91.0", exception_lowLatitue.localizedMessage)

        val exception_highLatitue = assertThrows<InvalidLatitude>("Should throw InvalidLatitude") {
            runBlocking {
                connector.getOneCall(LocationImpl(latitude = 91F, longitude = 0F))
            }
        }
        assertEquals("91.0", exception_highLatitue.localizedMessage)

        val exception_lowLongitude = assertThrows<InvalidLongitude>("Should throw InvalidLongitude") {
            runBlocking {
                connector.getOneCall(LocationImpl(latitude = 0F, longitude = -181F))
            }
        }
        assertEquals("-181.0", exception_lowLongitude.localizedMessage)

        val exception_highLongitude = assertThrows<InvalidLongitude>("Should throw InvalidLongitude") {
            runBlocking {
                connector.getOneCall(LocationImpl(latitude = -91F, longitude = 181F))
            }
        }
        assertEquals("181.0", exception_highLongitude.localizedMessage)
    }
}
