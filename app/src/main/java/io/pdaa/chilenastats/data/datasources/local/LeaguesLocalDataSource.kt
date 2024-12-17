package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.LeaguesDao
import io.pdaa.chilenastats.data.models.database.LeagueDB
import io.pdaa.chilenastats.data.models.database.asUiModel
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.domain.asDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LeaguesLocalDataSource(private val leaguesDao: LeaguesDao) {

    val leagues: Flow<List<LeagueUi>> = leaguesDao.getLeagues().map { leagues -> leagues.map { it.asUiModel() } }

    suspend fun insertLeagues(leagues: List<LeagueUi>) = leaguesDao.insertLeagues(leagues.asDBModel())

}

private fun LeagueUi.asDBModel(): LeagueDB {
    return LeagueDB(
        id = this.id,
        name = this.name,
        type = this.type,
        logo = this.logo,
        isFavourite = this.isFavourite,
        country = this.country.asDbModel(),
        season = this.season
    )
}

private fun List<LeagueUi>.asDBModel() = map { it.asDBModel() }