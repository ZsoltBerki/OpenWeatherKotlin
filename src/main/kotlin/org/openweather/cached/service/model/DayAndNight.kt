package org.openweather.cached.service.model

import java.time.ZonedDateTime

interface DayAndNight {
    /** Sunrise time, UTC */
    val sunrise: ZonedDateTime
    /** Sunset time, Unix, UTC */
    val sunset: ZonedDateTime
}
