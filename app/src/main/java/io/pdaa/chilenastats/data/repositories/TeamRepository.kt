package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class TeamRepository(
    private val remoteDataSource: TeamsRemoteDataSource,
    private val localDataSource: TeamsLocalDataSource,
    countryRepository: CountriesLocalDataSource
) {

    val teams: Flow<List<TeamUi>> = combine(
        localDataSource.teams,
        countryRepository.userCountry
    ) { localTeams, userCountry ->
        localTeams to userCountry
    }.onEach { (localTeams, userCountry) ->
        if (localTeams.isEmpty()) {
            val remoteTeams = remoteDataSource.fetchTeamsByCountryName(userCountry.countryName)
            localDataSource.insertTeams(remoteTeams)
        }
    }.filterNotNull().map { (teams, _) -> teams.map { it.asUiModel() } }


    suspend fun selectTeam(selectedTeam: TeamUi) {
        localDataSource.insertTeams(
            listOf(
                selectedTeam.copy(isSelected = selectedTeam.isSelected.not())
            )
        )
    }

}