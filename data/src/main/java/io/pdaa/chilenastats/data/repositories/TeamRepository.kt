package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TeamRepository(
    private val remoteDataSource: TeamsRemoteDataSource,
    private val localDataSource: TeamsLocalDataSource,
    private val regionDataSource: RegionDataSource,
    private val countryLocalDataSource: CountriesLocalDataSource,
    private val countriesRemoteDataSource: CountriesRemoteDataSource
) {

    val teams: Flow<List<TeamUi>>
        get() = combine(
            localDataSource.teams,
            countryLocalDataSource.countries,
        ) { localTeams, localCountries ->
            localTeams to localCountries
        }.onEach { (localTeams, localCountries) ->

            if (localTeams.isEmpty() && localCountries.isNotEmpty()) {
                val selectedCountry = localCountries.first { it.isSelected }
                val remoteTeams =
                    remoteDataSource.fetchTeamsByCountryName(selectedCountry.name)
                localDataSource.insertTeams(remoteTeams)
                return@onEach
            }

            if (localCountries.isEmpty() && localTeams.isEmpty()) {
                val remoteCountries = countriesRemoteDataSource.fetchCountries()
                countryLocalDataSource.insertCountries(remoteCountries)
                val lastRegion = regionDataSource.findLastRegion()
                remoteCountries.find { it.code == lastRegion }?.let { country ->
                    countryLocalDataSource.insertCountries(
                        listOf(country.copy(isSelected = true))
                    )
                    if (localTeams.isEmpty()) {
                        val remoteTeams =
                            remoteDataSource.fetchTeamsByCountryName(country.name)
                        localDataSource.insertTeams(remoteTeams)
                    }
                }
                return@onEach
            }

        }.filterNotNull()
            .map { (teams, _) -> teams.filter { it.national == false } }


    suspend fun selectTeam(selectedTeam: TeamUi) {
        localDataSource.insertTeams(
            listOf(
                selectedTeam.copy(isSelected = selectedTeam.isSelected.not())
            ),
        )
    }

    val areLocalTeamsEmpty = localDataSource.isEmpty

}