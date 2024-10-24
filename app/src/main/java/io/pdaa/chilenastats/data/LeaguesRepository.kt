package io.pdaa.chilenastats.data

import io.pdaa.chilenastats.data.models.local.LeagueUi

import io.pdaa.chilenastats.data.models.remote.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LeaguesRepository {
    suspend fun fetchLeaguesByCountry(countryCode: String): List<LeagueUi> =
        withContext(Dispatchers.IO) {
            delay(1000)
            FreeFootballDataClient.instance.fetchLeaguesByCountry(countryCode)
                .response.map { it.asUiModel().league }
        }

    suspend fun fetchLeagueById(leagueId: Int): LeagueUi = withContext(Dispatchers.IO){
        delay(1000)
        FreeFootballDataClient.instance.fetchLeaguesByCountry("ES")
            .response.map { it.asUiModel().league }.first{it.id == leagueId}
    }
}