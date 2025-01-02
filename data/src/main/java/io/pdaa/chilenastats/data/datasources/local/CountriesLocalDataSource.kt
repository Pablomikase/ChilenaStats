package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.domain.CountryUi
import kotlinx.coroutines.flow.Flow

interface CountriesLocalDataSource {
    val countries : Flow<List<CountryUi>>
    val userCountry: Flow<CountryUi>

    suspend fun insertCountries(countries: List<CountryUi>)
}

