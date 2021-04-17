package org.openweather.cached.service.model

import com.google.gson.annotations.SerializedName

data class OneCallResponse(
    @SerializedName("lat")
    override val latitude: Float,

    @SerializedName("lon")
    override val longitude: Float,

    @SerializedName("timezone")
    override val timezoneName: String,

    @SerializedName("timezone_offset")
    override val timezoneOffset: Long,

    @SerializedName("current")
    val current: CurrentWeather
) : Location, Timezone
