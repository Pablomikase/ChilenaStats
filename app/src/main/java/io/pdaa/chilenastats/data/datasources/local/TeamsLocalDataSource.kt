package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.TeamsDao
import io.pdaa.chilenastats.data.models.database.TeamDB

class TeamsLocalDataSource(private val teamsDao: TeamsDao) {

    suspend fun getTeams() = teamsDao.getTeams()

    suspend fun insertTeams(teams: List<TeamDB>) = teamsDao.insertTeams(teams)

    suspend fun isTeamsEmpty() = teamsDao.countTeams() == 0
}