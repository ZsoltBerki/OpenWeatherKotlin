package org.openweather.cached.service.gson

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.time.Month
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openweather.cached.service.model.DayAndNight
import java.time.ZonedDateTime

class OpenWeatherGsonTest {
    data class DayAndNightImpl(
        override val sunrise: ZonedDateTime,
        override val sunset: ZonedDateTime
    ) : DayAndNight

    companion object {
        const val testSunRiseEpoch = 634197600
        const val testSunSetEpoch = 634226400
    }

    @Test
    fun `ZonedDateTime deserialization`() {
        val testJson = """
            {
            	"sunrise": 634197600,
            	"sunset": 634226400
            }
        """.trimIndent()
        val result = gson.fromJson(testJson, DayAndNightImpl::class.java)
        assertEquals(634197600, result.sunrise.toEpochSecond())
        assertEquals(1990, result.sunrise.year)
        assertEquals(Month.FEBRUARY, result.sunrise.month)
        assertEquals(5, result.sunrise.dayOfMonth)
        assertEquals(6, result.sunrise.hour)
        assertEquals(0, result.sunrise.minute)
        assertEquals(0, result.sunrise.second)
        assertEquals(634226400, result.sunset.toEpochSecond())
        assertEquals(1990, result.sunset.year)
        assertEquals(Month.FEBRUARY, result.sunset.month)
        assertEquals(5, result.sunset.dayOfMonth)
        assertEquals(14, result.sunset.hour)
        assertEquals(0, result.sunset.minute)
        assertEquals(0, result.sunset.second)
    }

    fun `ZonedDateTime serialization`() {
        val testJson = """
            {
            	"sunrise": 634197600,
            	"sunset": 634226400
            }
        """.trimIndent()
        val result = gson.fromJson(testJson, DayAndNightImpl::class.java)
        val backToJson = gson.toJson(result)
        val jsonObject = JsonParser.parseString(backToJson).asJsonObject

        assertEquals(634197600L,jsonObject.get("sunrise").asBigInteger.toLong())
        assertEquals(634226400L,jsonObject.get("sunset").asBigInteger.toLong())
    }
}
