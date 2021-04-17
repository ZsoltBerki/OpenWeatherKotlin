package org.openweather.cached.service.model

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class CurrentWeather(
    @SerializedName("dt")
    val dateTime: ZonedDateTime,

    @SerializedName("sunrise")
    override val sunrise: ZonedDateTime,

    @SerializedName("sunset")
    override val sunset: ZonedDateTime,

    @SerializedName("temp")
    override val temperature: Float,

    @SerializedName("feels_like")
    override val feelsLike: Float,

    @SerializedName("pressure")
    override val pressure: Int,

    @SerializedName("humidity")
    override val humidity: Byte,

    @SerializedName("dew_point")
    override val dewPoint: Float,

    @SerializedName("clouds")
    override val cloudiness: Byte,

    @SerializedName("uvi")
    override val uvi: Float,

    @SerializedName("visibility")
    override val visibility: Int,

    @SerializedName("wind_speed")
    override val windSpeed: Float,

    @SerializedName("wind_gust")
    override val windGust: Float?,

    @SerializedName("wind_deg")
    override val windDegree: Float,

    @SerializedName("snow")
    val snowVolume: PrecipitationVolume?,

    @SerializedName("rain")
    val rainVolume: PrecipitationVolume?,

    @SerializedName("weather")
    val weatherMetadata: List<WeatherMetadata>
) : DayAndNight, Temperature, WeatherConditions, Wind
