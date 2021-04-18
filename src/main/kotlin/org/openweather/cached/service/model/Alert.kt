package org.openweather.cached.service.model

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class Alert(
    @SerializedName("sender_name")
    /**
     * Name of the alert source.
     */
    val sender: String,

    @SerializedName("event")
    /**
     * Alert event name
     */
    val event: String,

    @SerializedName("start")
    /**
     * Date and time of the start of the alert, Unix, UTC
     */
    val start: ZonedDateTime,

    @SerializedName("end")
    /**
     * Date and time of the end of the alert, Unix, UTC
     */
    val end: ZonedDateTime,

    @SerializedName("description")
    /**
     * Description of the alert
     */
    val description: String,
)
