package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.data.models.local.ResponseUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeaguesRepository(
    private val remoteDataSource: LeaguesRemoteDataSource
) {

    suspend fun fetchLeagueById(leagueId: Int): LeagueUi = withContext(Dispatchers.IO){
        remoteDataSource.fetchLeagueById(leagueId)
    }

    suspend fun fetchLeagues(): List<ResponseUi> = withContext(Dispatchers.IO){
        remoteDataSource.fetchLeagues()
    }
}