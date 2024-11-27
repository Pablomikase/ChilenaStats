package io.pdaa.chilenastats.data.datasources

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.TeamUi
import io.pdaa.chilenastats.data.models.remote.asUiModel

class TeamsRemoteDataSource {
    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> =
        FreeFootballDataClient.instance.fetchTeamsByCountryName(countryName)
            .response.map { it.team.asUiModel() }


}