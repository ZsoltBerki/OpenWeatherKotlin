package org.openweather.cached.service.model.response

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.PrecipitationLastHour

data class Snow(
    @SerializedName("1h")
    override val lastHour: Int?
) : PrecipitationLastHour