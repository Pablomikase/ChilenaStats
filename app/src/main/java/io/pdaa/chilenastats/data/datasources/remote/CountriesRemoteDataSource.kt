package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.database.CountryDB
import io.pdaa.chilenastats.data.models.remote.asDbModel

class CountriesRemoteDataSource {

    suspend fun fetchCountries(): List<CountryDB> =
        FreeFootballDataClient.instance.fetchCountries()
            .response
            .map {
                it.asDbModel()
            }.filter { it.countryCode != null }

}