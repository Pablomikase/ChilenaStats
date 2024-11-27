package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.RegionDataSource
import io.pdaa.chilenastats.data.models.local.CountryUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository(
    private val regionDataSource: RegionDataSource,
    private val remoteDataSource: CountriesRemoteDataSource
    ) {

    suspend fun fetchCountries(): List<CountryUi> = withContext(Dispatchers.IO){
        remoteDataSource.fetchCountries()
    }

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}