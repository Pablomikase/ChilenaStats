package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.data.models.local.CountryUi
import io.pdaa.chilenastats.data.models.local.asDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CountriesRepository(
    private val regionDataSource: RegionDataSource,
    private val remoteDataSource: CountriesRemoteDataSource,
    private val localDataSource: CountriesLocalDataSource
) {

    val countries: Flow<List<CountryUi>> = localDataSource.countries.onEach { localCountries ->
        if (localCountries.isEmpty()) {
            val lastRegion = regionDataSource.findLastRegion()
            val remoteCountries = remoteDataSource.fetchCountries()
                .sortedByDescending { it.countryCode == lastRegion }
                .map { it.copy(countryIsSelected = it.countryCode == lastRegion) }
            localDataSource.insertCountries(remoteCountries)
        }
    }.filterNotNull()
        .map { countries -> countries.map { it.asUiModel() }.sortedByDescending { it.isSelected } }

    suspend fun selectCountry(selectedCountry: CountryUi) {
        localDataSource.insertCountries(
            listOf(
                selectedCountry.copy(isSelected = selectedCountry.isSelected.not()).asDbModel()
            )
        )
    }

}