package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.database.TeamDB
import io.pdaa.chilenastats.data.models.remote.asDBModel

class TeamsRemoteDataSource {
    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamDB> =
        FreeFootballDataClient.instance.fetchTeamsByCountryName(countryName)
            .response.map { it.asDBModel()}

}