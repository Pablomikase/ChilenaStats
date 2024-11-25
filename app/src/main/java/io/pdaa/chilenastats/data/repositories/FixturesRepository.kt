package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.fixture.FixtureResponseUi
import io.pdaa.chilenastats.data.models.remote.fixture.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FixturesRepository {

    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureResponseUi> =
        withContext(Dispatchers.IO) {
            FreeFootballDataClient.instance.fetchFixturesByTeam(teamId = teamId)
                .response.map { it.asUiModel() }
        }

}