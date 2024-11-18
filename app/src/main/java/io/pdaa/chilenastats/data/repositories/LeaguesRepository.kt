package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.data.models.local.ResponseUi
import io.pdaa.chilenastats.data.models.remote.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeaguesRepository {
    suspend fun fetchLeaguesByCountry(countryCode: String): List<LeagueUi> =
        withContext(Dispatchers.IO) {
            FreeFootballDataClient.instance.fetchLeaguesByCountry(countryCode)
                .response.map { it.asUiModel().league }
        }

    suspend fun fetchLeagueById(leagueId: Int): LeagueUi = withContext(Dispatchers.IO){
        FreeFootballDataClient.instance.fetchLeaguesByCountry("ES")
            .response.map { it.asUiModel().league }.first{it.id == leagueId}
    }

    suspend fun fetchLeagues(): List<ResponseUi> = withContext(Dispatchers.IO){
        FreeFootballDataClient.instance.fetchLeagues()
            .response.asUiModel()
    }
}