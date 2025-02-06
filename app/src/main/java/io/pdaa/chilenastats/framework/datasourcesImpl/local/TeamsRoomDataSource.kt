package io.pdaa.chilenastats.framework.datasourcesImpl.local

import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.framework.models.database.TeamDB
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.VenueUi
import io.pdaa.chilenastats.framework.database.dao.TeamsDao
import io.pdaa.chilenastats.framework.models.database.VenueDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamsRoomDataSource(private val teamsDao: TeamsDao) : TeamsLocalDataSource {

    override val teams: Flow<List<TeamUi>> = teamsDao.getTeams().map { it.asUiModel() }

    override suspend fun insertTeams(teams: List<TeamUi>) = teamsDao.insertTeams(teams.asDBModel())

    override val favoriteTeams: Flow<List<TeamUi>> =
        teamsDao.getFavoriteTeams().map { teams -> teams.map { it.asUiModel() } }

    override val isEmpty: Flow<Boolean> = teamsDao.countTeams().map { it == 0 }

}

private fun TeamUi.asDBModel(): TeamDB {
    return TeamDB(
        id = this.id,
        name = this.name,
        logo = this.logo,
        isSelected = this.isFavourite,
        country = this.country,
        venue = this.venue?.asDBModel(),
        founded = this.founded,
        national = this.national
    )
}

@JvmName("asDBModelFromTeamUi")
private fun List<TeamUi>.asDBModel() = map { it.asDBModel() }

private fun VenueUi.asDBModel(): VenueDB = VenueDB(
    venueId = id ?: 0,
    venueName = name,
    venueAddress = address,
    venueCity = city,
    venueCapacity = capacity,
    venueSurface = surface,
    venueImage = image
)

@JvmName("asDBModelFromVenueUi")
private fun List<VenueUi>.asDBModel(): List<VenueDB> = this.map { it.asDBModel() }

fun TeamDB.asUiModel() = TeamUi(
    id = id,
    name = name,
    logo = logo,
    isFavourite = isSelected,
    country = country,
    founded = founded,
    national = national,
    venue = venue?.asUiModel()
)
private fun List<TeamDB>.asUiModel(): List<TeamUi> = map { it.asUiModel() }

fun VenueDB.asUiModel() = VenueUi(
    id = venueId,
    name = venueName ?: "",
    city = venueCity ?: "",
    capacity = venueCapacity ?: 0,
    surface = venueSurface ?: "",
    image = venueImage ?: "",
    address = venueAddress ?: ""
)

