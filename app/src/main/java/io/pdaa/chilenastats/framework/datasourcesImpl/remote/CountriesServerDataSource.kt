package io.pdaa.chilenastats.framework.datasourcesImpl.remote

import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.framework.models.database.CountryDB
import io.pdaa.chilenastats.framework.models.remote.asDbModel
import io.pdaa.chilenastats.framework.server.FootballDataService

class CountriesServerDataSource(private val footballDataService: FootballDataService) :
    CountriesRemoteDataSource {

    override suspend fun fetchCountries(): List<CountryDB> =
        footballDataService.fetchCountries()
            .response
            .map {
                it.asDbModel()
            }.filter { it.countryCode != null }

}