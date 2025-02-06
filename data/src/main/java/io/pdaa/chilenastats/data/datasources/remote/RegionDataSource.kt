package io.pdaa.chilenastats.data.datasources.remote


interface RegionDataSource {

    suspend fun findLastRegion(): String
}