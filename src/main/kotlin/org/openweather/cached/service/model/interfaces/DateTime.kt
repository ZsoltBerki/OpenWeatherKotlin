package org.openweather.cached.service.model.interfaces

import java.time.ZonedDateTime

interface DateTime {
    /**
     * Time of the forecasted data, unix, UTC
     */
    val dateTime: ZonedDateTime
}
