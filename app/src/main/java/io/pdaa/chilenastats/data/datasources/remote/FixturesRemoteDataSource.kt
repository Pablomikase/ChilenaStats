package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.remote.fixture.asUiModel
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi

class FixturesRemoteDataSource {
    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureContainerUi> =

        FreeFootballDataClient.instance.fetchFixturesByTeam(teamId = teamId)
            .response.map { it.asUiModel() }

}