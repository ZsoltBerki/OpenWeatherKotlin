package org.openweather.cached.service.model.response

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.DateTime
import org.openweather.cached.service.model.interfaces.Precipitation
import java.time.ZonedDateTime

data class MinutelyWeather(
    @SerializedName("dt")
    override val dateTime: ZonedDateTime,

    @SerializedName("precipitation")
    override val precipitation: Float,
) : DateTime, Precipitation
