package org.openweather.cached.service.model.response

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.Location
import org.openweather.cached.service.model.interfaces.Timezone

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
    val current: CurrentWeather?,

    @SerializedName("minutely")
    /**
     * Minute forecast weather data API response
     */
    val minutely: List<MinutelyWeather>?,

    @SerializedName("hourly")
    /**
     * Hourly forecast weather data API response
     */
    val hourly: List<HourlyWeather>?,

    @SerializedName("daily")
    /**
     * Daily forecast weather data API response
     */
    val daily: List<DailyWeather>?,

    @SerializedName("alerts")
    /**
     * National weather alerts data from major national weather warning systems
     */
    val alerts: List<Alert>?
) : Location, Timezone
