package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.domain.location.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

