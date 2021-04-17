package org.openweather.cached.service.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class ZonedDateTimeDeserializer : JsonDeserializer<ZonedDateTime> {
    companion object {
        private val UTC = ZoneId.of("Z")
    }
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ZonedDateTime {
        val unixTimestamp = json?.asBigInteger?.toLong()
        return Instant.ofEpochSecond(unixTimestamp!!).atZone(UTC)
    }
}
