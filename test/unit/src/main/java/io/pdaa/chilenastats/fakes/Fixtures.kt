package io.pdaa.chilenastats.fakes

import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

fun buildFixturesRepositoryWith(
    localData: List<FixtureContainerUi> = emptyList(),
    remoteData: List<FixtureContainerUi> = emptyList(),
    teams: List<TeamUi> = emptyList()
): FixturesRepository {


    return FixturesRepository(
        remoteDataSource = FakeFixturesRemoteDataSource().apply { remoteFixtures = remoteData },
        fixturesLocalDataSource = FakeFixturesLocalDataSource().apply {
            inMemoryFixtures.value = localData
        },
        teamsRepository = FakeTeamsLocalDataSource().apply { inMemoryTeams.value = teams }
    )
}

class FakeFixturesRemoteDataSource : FixturesRemoteDataSource {
    var remoteFixtures = emptyList<FixtureContainerUi>()
    override suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureContainerUi> = remoteFixtures
}

class FakeFixturesLocalDataSource : FixturesLocalDataSource {
    val inMemoryFixtures = MutableStateFlow<List<FixtureContainerUi>>(emptyList())
    override val fixtures: Flow<List<FixtureContainerUi>>
        get() = inMemoryFixtures

    override suspend fun insertFixtures(fixtures: List<FixtureContainerUi>, teamOwnerId: Int) {
        inMemoryFixtures.value = fixtures
    }

    override fun getFixturesByTeam(teamId: Int): Flow<List<FixtureContainerUi>> {
        return inMemoryFixtures
    }


}