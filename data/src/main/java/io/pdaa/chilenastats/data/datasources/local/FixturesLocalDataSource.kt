package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.flow.Flow

interface FixturesLocalDataSource {
    val fixtures: Flow<List<FixtureContainerUi>>

    suspend fun insertFixtures(fixtures: List<FixtureContainerUi>, teamOwnerId: Int)
    fun getFixturesByTeam(teamId: Int): Flow<List<FixtureContainerUi>>
}


