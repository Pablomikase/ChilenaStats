package io.pdaa.chilenastats.data.datasources.remote

import android.location.Geocoder
import android.location.Location
import io.pdaa.chilenastats.ui.common.getFromLocationCompat

const val DEFAULT_REGION = "ES"

interface RegionDataSource {

    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}

class GeocoderRegionSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) :  RegionDataSource {

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }

}