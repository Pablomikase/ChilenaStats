package io.pdaa.chilenastats.framework.datasourcesImpl.local

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.framework.models.database.LeagueDB

import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.framework.database.dao.LeaguesDao
import io.pdaa.chilenastats.framework.models.database.CountryDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LeaguesRoomDataSource(private val leaguesDao: LeaguesDao) : LeaguesLocalDataSource {

    override val leagues: Flow<List<LeagueUi>> = leaguesDao.getLeagues().map { leagues -> leagues.map { it.asUiModel() } }

    override suspend fun insertLeagues(leagues: List<LeagueUi>) = leaguesDao.insertLeagues(leagues.asDBModel())

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

private fun CountryUi.asDbModel(): CountryDB = CountryDB(
    countryName = name,
    countryCode = code,
    countryFlag = flag,
    countryIsSelected = isSelected
)

private fun List<CountryUi>.asDbModel(): List<CountryDB> =
    this.map { it.asDbModel() }

private fun LeagueDB.asUiModel() = LeagueUi(
    id = id,
    logo = logo,
    name = name,
    type = type,
    isFavourite = isFavourite,
    country = country.asUiModel(),
    season = season
)