package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.domain.TeamUi

interface TeamsRemoteDataSource {
    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi>
}
