package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.TeamsRemoteDataSource
import io.pdaa.chilenastats.data.models.local.TeamUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository( private val remoteDataSource: TeamsRemoteDataSource) {

    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> = withContext(Dispatchers.IO){
        remoteDataSource.fetchTeamsByCountryName(countryName)
    }

}