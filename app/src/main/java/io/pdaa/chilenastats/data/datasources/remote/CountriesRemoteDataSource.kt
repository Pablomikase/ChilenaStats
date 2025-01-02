package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.framework.models.database.CountryDB

interface CountriesRemoteDataSource {
    suspend fun fetchCountries(): List<CountryDB>
}

