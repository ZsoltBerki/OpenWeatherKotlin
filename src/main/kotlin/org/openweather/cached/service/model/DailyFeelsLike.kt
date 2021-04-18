package org.openweather.cached.service.model

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.TemperatureOfDay

data class DailyFeelsLike(
    @SerializedName("morn")
    override val morning: Float,

    @SerializedName("day")
    override val day: Float,

    @SerializedName("eve")
    override val evening: Float,

    @SerializedName("night")
    override val night: Float,
) : TemperatureOfDay
