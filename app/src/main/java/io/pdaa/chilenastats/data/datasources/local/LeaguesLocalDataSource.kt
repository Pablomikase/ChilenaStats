package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.LeaguesDao
import io.pdaa.chilenastats.data.models.database.LeagueDB
import kotlinx.coroutines.flow.Flow

class LeaguesLocalDataSource(private val leaguesDao: LeaguesDao) {

    val leagues: Flow<List<LeagueDB>> = leaguesDao.getLeagues()

    suspend fun insertLeagues(leagues: List<LeagueDB>) = leaguesDao.insertLeagues(leagues)


}