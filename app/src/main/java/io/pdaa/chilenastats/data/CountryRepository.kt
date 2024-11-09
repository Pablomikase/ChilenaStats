package io.pdaa.chilenastats.data

import io.pdaa.chilenastats.data.models.local.CountryUi
import io.pdaa.chilenastats.data.models.remote.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CountryRepository {

    suspend fun fetchCountries(): List<CountryUi> = withContext(Dispatchers.IO) {
        delay(1000)
        FreeFootballDataClient.instance.fetchCountries()
            .response.map { it.asUiModel() }
    }

}