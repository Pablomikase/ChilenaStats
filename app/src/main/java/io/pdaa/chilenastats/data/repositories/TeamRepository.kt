package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.TeamUi
import io.pdaa.chilenastats.data.models.remote.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository {

    suspend fun fetchTeamsByLeague(leagueId: Int): List<TeamUi> = withContext(Dispatchers.IO){
        emptyList()
    }

    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> = withContext(Dispatchers.IO){
        FreeFootballDataClient.instance.fetchTeamsByCountryName(countryName)
            .response.map { it.team.asUiModel() }
    }

}