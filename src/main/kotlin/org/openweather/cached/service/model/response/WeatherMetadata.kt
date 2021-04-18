package org.openweather.cached.service.model.response

import com.google.gson.annotations.SerializedName

data class WeatherMetadata(
    @SerializedName("id")
    /**
     * Weather condition id
     */
    val id: Int,

    @SerializedName("main")
    /**
     * Group of weather parameters (Rain, Snow, Extreme etc.)
     */
    val name: String,

    /**
     * Weather condition within the group.
     */
    @SerializedName("description")
    val description: String,

    /**
     * Weather icon id.
     */
    @SerializedName("icon")
    val icons: String
)
