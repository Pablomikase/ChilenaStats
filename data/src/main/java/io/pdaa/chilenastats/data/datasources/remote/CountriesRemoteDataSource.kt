package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.domain.CountryUi

interface CountriesRemoteDataSource {
    suspend fun fetchCountries(): List<CountryUi>
}

