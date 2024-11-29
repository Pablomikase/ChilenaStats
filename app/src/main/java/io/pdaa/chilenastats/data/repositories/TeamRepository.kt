package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.data.models.local.TeamUi

class TeamRepository(
    private val remoteDataSource: TeamsRemoteDataSource,
    private val localDataSource: TeamsLocalDataSource
) {

    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> {
        if (localDataSource.isTeamsEmpty()) {
            remoteDataSource.fetchTeamsByCountryName(countryName).let { teamDBs ->
                localDataSource.insertTeams(teamDBs)
                return teamDBs.map { it.asUiModel() }
            }
        }
        return localDataSource.getTeams().map { it.asUiModel() }
    }

}