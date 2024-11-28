package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.LeaguesDao
import io.pdaa.chilenastats.data.models.database.LeagueDB

class LeaguesLocalDataSource(private val leaguesDao: LeaguesDao) {

    suspend fun getLeagues() = leaguesDao.getLeagues()

    suspend fun insertLeagues(leagues: List<LeagueDB>) = leaguesDao.insertLeagues(leagues)

    suspend fun isLeaguesEmpty() = leaguesDao.countLeagues() == 0

}