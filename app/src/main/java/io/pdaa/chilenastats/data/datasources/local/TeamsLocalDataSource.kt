package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.TeamsDao
import io.pdaa.chilenastats.data.models.database.TeamDB
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.asDBModel
import kotlinx.coroutines.flow.Flow

class TeamsLocalDataSource(private val teamsDao: TeamsDao) {

    val teams: Flow<List<TeamDB>> = teamsDao.getTeams()

    suspend fun insertTeams(teams: List<TeamUi>) = teamsDao.insertTeams(teams.asDBModel())

    val favoriteTeams: Flow<List<TeamDB>> = teamsDao.getFavoriteTeams()

}

fun TeamUi.asDBModel(): TeamDB {
    return TeamDB(
        id = this.id,
        name = this.name,
        logo = this.logo,
        isSelected = this.isSelected,
        country = this.country,
        venue = this.venue?.asDBModel(),
        founded = this.founded,
        national = this.national
    )
}

private fun List<TeamUi>.asDBModel() = map { it.asDBModel() }