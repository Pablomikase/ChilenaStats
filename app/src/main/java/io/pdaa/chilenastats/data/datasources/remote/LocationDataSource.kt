package io.pdaa.chilenastats.data.datasources.remote

import android.location.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

