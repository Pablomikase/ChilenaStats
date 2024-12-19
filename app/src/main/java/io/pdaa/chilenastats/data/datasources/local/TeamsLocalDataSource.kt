package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.TeamsDao
import io.pdaa.chilenastats.data.models.database.TeamDB
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.asDBModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamsLocalDataSource(private val teamsDao: TeamsDao) {

    val teams: Flow<List<TeamDB>> = teamsDao.getTeams()

    suspend fun insertTeams(teams: List<TeamUi>) = teamsDao.insertTeams(teams.asDBModel())

    val favoriteTeams: Flow<List<TeamUi>> = teamsDao.getFavoriteTeams().map { teams -> teams.map { it.asUiModel() } }

    val  isEmpty: Flow<Boolean> = teamsDao.countTeams().map { it == 0 }

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