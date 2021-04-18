package org.openweather.cached.service.model

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.DateTime
import org.openweather.cached.service.model.interfaces.DayAndNight
import org.openweather.cached.service.model.interfaces.WeatherConditions
import org.openweather.cached.service.model.interfaces.Wind
import java.time.ZonedDateTime

data class DailyWeather(
    @SerializedName("dt")
    override val dateTime: ZonedDateTime,

    @SerializedName("sunrise")
    override val sunrise: ZonedDateTime,

    @SerializedName("sunset")
    override val sunset: ZonedDateTime,

    @SerializedName("temp")
    /**
     * Temperatures for the day.
     *
     * Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
     */
    val dailyTemperature: DailyTemperature,

    @SerializedName("feels_like")
    /**
     * Temperatures for the day.
     * This accounts for the human perception of weather.
     *
     * Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
     */
    val dailyFeelsLike: DailyFeelsLike,

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

    @SerializedName("pop")
    /**
     * Probability of precipitation
     *
     * from 0 (none) to 1 (certain)
     */
    val precipitationProbability: Float,

    @SerializedName("snow")
    /**
     * Precipitation volume, mm
     */
    val snowVolume: Float,

    @SerializedName("rain")
    /**
     * Snow volume, mm
     */
    val rainVolume: Float,

    @SerializedName("weather")
    val weatherMetadata: List<WeatherMetadata>
) : DayAndNight, WeatherConditions, Wind, DateTime
