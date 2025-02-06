package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi

interface FixturesRemoteDataSource {
    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureContainerUi>
}

