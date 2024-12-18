package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class FixturesRepository(
    private val remoteDataSource: FixturesRemoteDataSource,
    teamsLocalDataSource: TeamsLocalDataSource,
    private val fixturesLocalDataSource: FixturesLocalDataSource
) {

    val fixturesFavouriteTeam: Flow<List<FixtureContainerUi>> = combine(
        teamsLocalDataSource.favoriteTeams,
        fixturesLocalDataSource.fixtures
    ) { localTeams, localFixtures ->
        localTeams to localFixtures
    }.onEach { (localTeams, localFixtures) ->
        if (localFixtures.isEmpty()) {
            val remoteFixtures = remoteDataSource.fetchFixturesByTeam(localTeams.first().id)
            fixturesLocalDataSource.insertFixtures(remoteFixtures, localTeams.first().id)
        }
    }.filterNotNull().map { (_, localFixtures) -> localFixtures }

    fun fixturesByTeam(teamId: Int): Flow<List<FixtureContainerUi>> =
        fixturesLocalDataSource.getFixturesByTeam(teamId).onEach { localFixtures ->
            if (localFixtures.isEmpty()) {
                val remoteFixtures = remoteDataSource.fetchFixturesByTeam(teamId)
                fixturesLocalDataSource.insertFixtures(remoteFixtures, teamId)
            }
        }

}


