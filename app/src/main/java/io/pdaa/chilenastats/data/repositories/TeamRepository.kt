package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.framework.models.database.asUiModel
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TeamRepository(
    remoteDataSource: TeamsRemoteDataSource,
    private val localDataSource: TeamsLocalDataSource,
    regionDataSource: RegionDataSource,
    countryLocalDataSource: CountriesLocalDataSource,
    countriesRemoteDataSource: CountriesRemoteDataSource
) {

    val teams: Flow<List<TeamUi>> = combine(
        localDataSource.teams,
        countryLocalDataSource.countries,
    ) { localTeams, localCountries ->
        localTeams to localCountries
    }.onEach { (localTeams, localCountries) ->

        if (localTeams.isEmpty() && localCountries.isNotEmpty()) {
            val selectedCountry = localCountries.first { it.countryIsSelected }
            val remoteTeams =
                remoteDataSource.fetchTeamsByCountryName(selectedCountry.countryName)
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
        .map { (teams, _) -> teams.filter { it.national == false }.map { it.asUiModel() } }


    suspend fun selectTeam(selectedTeam: TeamUi) {
        localDataSource.insertTeams(
            listOf(
                selectedTeam.copy(isSelected = selectedTeam.isSelected.not())
            ),
        )
    }

    val favouriteTeams: Flow<List<TeamUi>> = localDataSource.favoriteTeams

    val areLocalTeamsEmpty = localDataSource.isEmpty

}