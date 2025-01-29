package io.pdaa.chilenastats.framework.datasourcesImpl.remote

import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.framework.models.remote.TeamRemoteResponse
import io.pdaa.chilenastats.framework.models.remote.asUiModel
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.framework.server.FootballDataService

class TeamsServerDataSource(private val footballDataService: FootballDataService) :
    TeamsRemoteDataSource {
    override suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> =
        footballDataService.fetchTeamsByCountryName(countryName)
            .response.map { it.asUiModel() }

    override suspend fun fetchTeamsByLeagueId(leagueId: Int, season: Int): List<TeamUi> =
        footballDataService.fetchTeamsByLeagueId(leagueId = leagueId, season = season)
            .response.map { it.asUiModel() }

    override suspend fun fetchTeamsByQuery(query: String): List<TeamUi> =
        footballDataService.fetchTeamsByQuery(query)
            .response.map { it.asUiModel() }
}


private fun TeamRemoteResponse.asUiModel(): TeamUi = TeamUi(
    id = team.id,
    name = team.name ?: "",
    logo = team.logo ?: "",
    country = team.country ?: "",
    isFavourite = false,
    founded = team.founded,
    national = team.national,
    venue = venue.asUiModel()
)