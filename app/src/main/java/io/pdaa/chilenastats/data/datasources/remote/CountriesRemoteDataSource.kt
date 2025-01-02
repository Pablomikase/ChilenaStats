package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FootballDataService
import io.pdaa.chilenastats.data.models.database.CountryDB
import io.pdaa.chilenastats.data.models.remote.asDbModel

interface CountriesRemoteDataSource {
    suspend fun fetchCountries(): List<CountryDB>
}

class CountriesServerDataSource(private val footballDataService: FootballDataService) : CountriesRemoteDataSource {

    override suspend fun fetchCountries(): List<CountryDB> =
        footballDataService.fetchCountries()
            .response
            .map {
                it.asDbModel()
            }.filter { it.countryCode != null }

}