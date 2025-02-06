package io.pdaa.chilenastats.framework.datasourcesImpl.sensors

import android.location.Geocoder
import io.pdaa.chilenastats.data.datasources.remote.LocationDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.domain.location.Location
import io.pdaa.chilenastats.ui.common.getFromLocationCompat

const val DEFAULT_REGION = "ES"

class GeocoderRegionSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }

}