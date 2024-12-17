package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.remote.LeagueRemoteResponse
import io.pdaa.chilenastats.data.models.remote.asUiModel
import io.pdaa.chilenastats.domain.LeagueUi

class LeaguesRemoteDataSource {

    suspend fun fetchLeagues(): List<LeagueUi> =
        FreeFootballDataClient.instance.fetchLeagues()
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