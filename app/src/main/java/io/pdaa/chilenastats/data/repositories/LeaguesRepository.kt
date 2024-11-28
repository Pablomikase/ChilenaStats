package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.data.models.local.LeagueUi

class LeaguesRepository(
    private val remoteDataSource: LeaguesRemoteDataSource,
    private val localDataSource: LeaguesLocalDataSource
) {

    suspend fun fetchLeagues(): List<LeagueUi> {
        if(localDataSource.isLeaguesEmpty()){
            remoteDataSource.fetchLeagues().let { leagueDBs ->
                localDataSource.insertLeagues(leagueDBs)
                return leagueDBs.map { it.asUiModel() }
            }
        }
        return remoteDataSource.fetchLeagues().map { it.asUiModel() }
    }
}