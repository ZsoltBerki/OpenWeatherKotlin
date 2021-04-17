package org.openweather.cached.service.gson

import com.google.gson.GsonBuilder
import java.time.ZonedDateTime

val gson = GsonBuilder()
    .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeDeserializer())
    .create()
