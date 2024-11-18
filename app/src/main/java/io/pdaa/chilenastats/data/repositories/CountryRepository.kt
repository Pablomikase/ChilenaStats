package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.CountryUi
import io.pdaa.chilenastats.data.models.remote.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository {

    suspend fun fetchCountries(): List<CountryUi> = withContext(Dispatchers.IO) {
        FreeFootballDataClient.instance.fetchCountries()
            .response.map { it.asUiModel() }
    }

}