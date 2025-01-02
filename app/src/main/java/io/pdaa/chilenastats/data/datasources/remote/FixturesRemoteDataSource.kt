package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FootballDataService
import io.pdaa.chilenastats.data.models.remote.fixture.asUiModel
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi

interface FixturesRemoteDataSource {
    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureContainerUi>
}

class FixturesServerDataSource(private val footballDataService: FootballDataService) : FixturesRemoteDataSource {
    override suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureContainerUi> =

        footballDataService.fetchFixturesByTeam(teamId = teamId)
            .response.map { it.asUiModel() }

}