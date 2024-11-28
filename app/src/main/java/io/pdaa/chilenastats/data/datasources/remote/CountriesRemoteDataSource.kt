package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.CountryUi
import io.pdaa.chilenastats.data.models.remote.asUiModel

class CountriesRemoteDataSource {

    suspend fun fetchCountries(): List<CountryUi> =
        FreeFootballDataClient.instance.fetchCountries()
            .response
            .map {
                it.asUiModel()
            }.filter { it.code != null }

}