package io.pdaa.chilenastats.data.datasources

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.CountryUi
import io.pdaa.chilenastats.data.models.remote.asUiModel

class CountriesRemoteDataSource {

    suspend fun fetchCountries(): List<CountryUi> =
        FreeFootballDataClient.instance.fetchCountries()
            .response
            .map {
                it.asUiModel()
            }

}