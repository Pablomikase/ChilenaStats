package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.domain.TeamUi

interface TeamsRemoteDataSource {
    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi>
    suspend fun fetchTeamsByLeagueId(leagueId: Int, season: Int): List<TeamUi>
    suspend fun fetchTeamsByQuery(query: String): List<TeamUi>
}
