package org.openweather.cached.service.model

import com.google.gson.annotations.SerializedName
import org.openweather.cached.service.model.interfaces.PrecipitationLastHour

data class Rain(
    @SerializedName("1h")
    override val lastHour: Int?
) : PrecipitationLastHour
