package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.data.models.local.asDBModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class LeaguesRepository(
    private val remoteDataSource: LeaguesRemoteDataSource,
    private val localDataSource: LeaguesLocalDataSource
) {

    val leagues: Flow<List<LeagueUi>> = localDataSource.leagues.onEach { localLeagues ->
        if (localLeagues.isEmpty()) {
            val remoteLeagues = remoteDataSource.fetchLeagues()
            localDataSource.insertLeagues(remoteLeagues)
        }
    }.filterNotNull().map { leagues -> leagues.map { it.asUiModel() } }

    suspend fun selectLeague(selectedLeague: LeagueUi) {
        localDataSource.insertLeagues(
            listOf(
                selectedLeague.copy(isSelected = selectedLeague.isSelected.not()).asDBModel()
            )
        )
    }


}