package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource

import io.pdaa.chilenastats.domain.LeagueUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class LeaguesRepository(
    private val remoteDataSource: LeaguesRemoteDataSource,
    private val localDataSource: LeaguesLocalDataSource
) {

    val leagues: Flow<List<LeagueUi>>
        get() = localDataSource.leagues.onEach { localLeagues ->
            if (localLeagues.isEmpty()) {
                val remoteLeagues = remoteDataSource.fetchLeagues()
                localDataSource.insertLeagues(remoteLeagues)
            }
        }

    suspend fun selectLeague(selectedLeague: LeagueUi) {
        localDataSource.insertLeagues(
            listOf(
                selectedLeague.copy(isFavourite = selectedLeague.isFavourite.not())
            )
        )
    }


}