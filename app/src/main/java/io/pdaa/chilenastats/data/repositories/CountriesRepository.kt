package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.data.models.local.CountryUi
import io.pdaa.chilenastats.data.models.local.asDbModel

class CountriesRepository(
    private val regionDataSource: RegionDataSource,
    private val remoteDataSource: CountriesRemoteDataSource,
    private val localDataSource: CountriesLocalDataSource
    ) {

    suspend fun fetchCountries(): List<CountryUi> {
        if(localDataSource.isCountriesEmpty()){
            remoteDataSource.fetchCountries().let { countryUis ->
                localDataSource.insertCountries(countryUis.map { it.asDbModel() })
                return countryUis
            }
        }
        return localDataSource.getCountries().map { it.asUiModel() }
    }

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}