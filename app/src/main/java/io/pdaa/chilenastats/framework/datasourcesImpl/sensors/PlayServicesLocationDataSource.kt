package io.pdaa.chilenastats.framework.datasourcesImpl.sensors

import android.annotation.SuppressLint
import android.location.Location as AndroidLocation
import com.google.android.gms.location.FusedLocationProviderClient
import io.pdaa.chilenastats.data.datasources.remote.LocationDataSource
import io.pdaa.chilenastats.domain.location.Location
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(private val fusedLocationClient: FusedLocationProviderClient) :
    LocationDataSource {
    override suspend fun findLastLocation(): Location? = fusedLocationClient.lastLocation()
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location?.toDomainLocation())
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}

private fun AndroidLocation.toDomainLocation(): Location = Location(latitude, longitude)