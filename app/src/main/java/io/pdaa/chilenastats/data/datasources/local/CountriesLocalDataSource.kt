package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.framework.models.database.CountryDB
import kotlinx.coroutines.flow.Flow

interface CountriesLocalDataSource {
    val countries : Flow<List<CountryDB>>
    val userCountry: Flow<CountryDB>

    suspend fun insertCountries(countries: List<CountryUi>)
}

