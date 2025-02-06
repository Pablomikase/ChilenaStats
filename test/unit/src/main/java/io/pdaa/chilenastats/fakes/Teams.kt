package io.pdaa.chilenastats.fakes

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildTeamsRepositoryWith(
    localData: List<TeamUi> = emptyList(),
    remoteData: List<TeamUi> = emptyList(),
    localCountriesData: List<CountryUi> = emptyList(),
    remoteCountriesData: List<CountryUi> = emptyList()
): TeamRepository = TeamRepository(
    remoteDataSource = FakeTeamsRemoteDataSource().apply { remoteTeams = remoteData },
    localDataSource = FakeTeamsLocalDataSource().apply { inMemoryTeams.value = localData },
    regionDataSource = FakeRegionDataSource(),
    countryLocalDataSource = FakeCountriesLocalDataSource().apply { inMemoryCountries.value = localCountriesData },
    countriesRemoteDataSource = FakeCountriesRemoteDataSource().apply { remoteCountries = remoteCountriesData }


    )

class FakeTeamsRemoteDataSource : TeamsRemoteDataSource {
    var remoteTeams = emptyList<TeamUi>()

    override suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> = remoteTeams
}

class FakeTeamsLocalDataSource : TeamsLocalDataSource {

    val inMemoryTeams = MutableStateFlow<List<TeamUi>>(emptyList())

    override val teams: Flow<List<TeamUi>>
        get() = inMemoryTeams

    override val favoriteTeams: Flow<List<TeamUi>>
        get() = inMemoryTeams.map { teamUis -> teamUis.filter { it.isFavourite } }

    override val isEmpty: Flow<Boolean>
        get() = inMemoryTeams.map { teamUis -> teamUis.isEmpty() }

    override suspend fun insertTeams(teams: List<TeamUi>) {
        inMemoryTeams.value = teams
    }

}

class FakeRegionDataSource : RegionDataSource {
    var region = "ES"
    override suspend fun findLastRegion(): String = region
}

class FakeCountriesLocalDataSource : CountriesLocalDataSource {

    val inMemoryCountries = MutableStateFlow<List<CountryUi>>(emptyList())

    override val countries: Flow<List<CountryUi>>
        get() = inMemoryCountries
    override val userCountry: Flow<CountryUi>
        get() = inMemoryCountries.map { countries -> countries.first { it.isSelected } }

    override suspend fun insertCountries(countries: List<CountryUi>) {
        inMemoryCountries.value = countries
    }
}

class FakeCountriesRemoteDataSource : CountriesRemoteDataSource {

    var remoteCountries = emptyList<CountryUi>()

    override suspend fun fetchCountries(): List<CountryUi> = remoteCountries

}

