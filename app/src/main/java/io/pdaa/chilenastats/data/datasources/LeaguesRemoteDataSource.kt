package io.pdaa.chilenastats.data.datasources

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.data.models.local.ResponseUi
import io.pdaa.chilenastats.data.models.remote.asUiModel

class LeaguesRemoteDataSource {

    suspend fun fetchLeagueById(leagueId: Int): LeagueUi =
        FreeFootballDataClient.instance.fetchLeaguesByCountry("ES")
            .response.map { it.asUiModel().league }.first { it.id == leagueId }


    suspend fun fetchLeagues(): List<ResponseUi> =
        FreeFootballDataClient.instance.fetchLeagues()
            .response.asUiModel()

}