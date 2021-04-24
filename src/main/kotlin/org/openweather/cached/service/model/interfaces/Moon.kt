package org.openweather.cached.service.model.interfaces

import java.time.ZonedDateTime

interface Moon {
    /**
     * The time of when the moon rises for this day, Unix, UTC
     */
    val moonRise: ZonedDateTime

    /**
     * The time of when the moon sets for this day, Unix, UTC
     */
    val moonSet: ZonedDateTime

    /**
     * Moon phase.
     * - 0 and 1 are 'new moon',
     * - 0.25 is 'first quarter moon',
     * - 0.5 is 'full moon'
     * - and 0.75 is 'last quarter moon'.
     *
     * The periods in between are called 'waxing crescent', 'waxing gibous', 'waning gibous', and 'waning crescent',
     * respectively.
     */
    val moonPhase: Float
}
