package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.TeamsDao
import io.pdaa.chilenastats.data.models.database.TeamDB
import kotlinx.coroutines.flow.Flow

class TeamsLocalDataSource(private val teamsDao: TeamsDao) {

    val teams: Flow<List<TeamDB>> = teamsDao.getTeams()

    suspend fun insertTeams(teams: List<TeamDB>) = teamsDao.insertTeams(teams)

}