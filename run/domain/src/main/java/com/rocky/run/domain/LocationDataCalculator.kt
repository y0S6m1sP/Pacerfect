package com.rocky.run.domain

import com.rocky.core.domain.location.LocationTimestamp
import kotlin.math.roundToInt

object LocationDataCalculator {

    fun getTotalDistanceMeters(locations: List<List<LocationTimestamp>>): Int {
        return locations
            .sumOf { timestampsPerLine ->
                timestampsPerLine.zipWithNext { first, second ->
                    first.location.location.distanceTo(second.location.location)
                }.sum().roundToInt()
            }
    }

}
