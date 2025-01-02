package io.pdaa.chilenastats.data.datasources.remote

import android.location.Location



interface RegionDataSource {

    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}