package org.openweather.cached.service.model.response

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.TemperatureLimitsOfDay
import org.openweather.cached.service.model.interfaces.TemperatureOfDay

data class DailyTemperature(
    @SerializedName("morn")
    override val morning: Float,

    @SerializedName("day")
    override val day: Float,

    @SerializedName("eve")
    override val evening: Float,

    @SerializedName("night")
    override val night: Float,

    @SerializedName("min")
    override val min: Float,

    @SerializedName("max")
    override val max: Float

) : TemperatureOfDay, TemperatureLimitsOfDay
