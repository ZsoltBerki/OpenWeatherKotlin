package org.openweather.cached.service.model.interfaces

interface Timezone {
    /**
     * Timezone name for the requested location
     */
    val timezoneName: String

    /**
     * Shift in seconds from UTC
     */
    val timezoneOffset: Long
}
