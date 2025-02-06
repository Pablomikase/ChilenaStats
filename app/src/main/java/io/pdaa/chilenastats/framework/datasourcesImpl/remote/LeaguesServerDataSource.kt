package io.pdaa.chilenastats.framework.datasourcesImpl.remote

import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.framework.models.remote.LeagueRemoteResponse
import io.pdaa.chilenastats.framework.models.remote.asUiModel
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.framework.server.FootballDataService

class LeaguesServerDataSource(private val footballDataService: FootballDataService) :
    LeaguesRemoteDataSource {

    override suspend fun fetchLeagues(): List<LeagueUi> =
        footballDataService.fetchLeagues()
            .response.map { it.asUiModel() }
}

private fun LeagueRemoteResponse.asUiModel(): LeagueUi = LeagueUi(
    id = league.id,
    logo = league.logo,
    name = league.name,
    type = league.type,
    country = country.asUiModel(),
    season = null,
    isFavourite = false
)