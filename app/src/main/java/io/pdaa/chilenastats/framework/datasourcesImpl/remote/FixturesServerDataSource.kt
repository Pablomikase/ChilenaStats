package io.pdaa.chilenastats.framework.datasourcesImpl.remote

import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.framework.models.remote.fixture.asUiModel
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.framework.server.FootballDataService

class FixturesServerDataSource(private val footballDataService: FootballDataService) :
    FixturesRemoteDataSource {
    override suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureContainerUi> =

        footballDataService.fetchFixturesByTeam(teamId = teamId)
            .response.map { it.asUiModel() }

}