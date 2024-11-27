package io.pdaa.chilenastats.data.datasources

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.fixture.FixtureResponseUi
import io.pdaa.chilenastats.data.models.remote.fixture.asUiModel

class FixturesRemoteDataSource {
    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureResponseUi> =

        FreeFootballDataClient.instance.fetchFixturesByTeam(teamId = teamId)
            .response.map { it.asUiModel() }

}