package org.openweather.cached.service.rest

import org.openweather.cached.service.model.Location

fun validateLocation(location: Location) {
    validateLongitude(location.longitude)
    validateLatitude(location.latitude)
}

class InvalidLatitude(latitude: Float) : Exception(latitude.toString())
const val LATITUDE_MAX = 90F
const val LATITUDE_MIN = -90F

fun validateLatitude(latitude: Float) {
    if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
        throw InvalidLatitude(latitude)
    }
}

class InvalidLongitude(longitude: Float) : Exception(longitude.toString())
const val LONGITUDE_MAX = 180F
const val LONGItUDE_MIN = -180F

fun validateLongitude(longitude: Float) {
    if (longitude < LONGItUDE_MIN || longitude > LONGITUDE_MAX) {
        throw InvalidLongitude(longitude)
    }
}
