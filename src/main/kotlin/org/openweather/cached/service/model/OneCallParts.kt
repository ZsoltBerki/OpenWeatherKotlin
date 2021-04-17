package org.openweather.cached.service.model

enum class OneCallParts(val fieldName: String) {
    CURRENT("current"),
    MINUTELY("minutely"),
    HOURLY("hourly"),
    DAILY("daily"),
    ALERTS("alerts")
}
