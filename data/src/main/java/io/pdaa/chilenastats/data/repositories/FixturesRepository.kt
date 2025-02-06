package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class FixturesRepository(
    private val remoteDataSource: FixturesRemoteDataSource,
    private val fixturesLocalDataSource: FixturesLocalDataSource,
    private val teamsRepository: TeamsLocalDataSource
) {

    private fun fixturesByTeam(teamId: Int): Flow<List<FixtureContainerUi>> =
        fixturesLocalDataSource.getFixturesByTeam(teamId).onEach { localFixtures ->
            if (localFixtures.isEmpty()) {
                val remoteFixtures = remoteDataSource.fetchFixturesByTeam(teamId)
                fixturesLocalDataSource.insertFixtures(remoteFixtures, teamId)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fixturesFromFavouriteTeams(): Flow<List<Pair<TeamUi, List<FixtureContainerUi>>>> {
        return teamsRepository.favoriteTeams
            .flatMapLatest { favouriteTeams ->
                combine(favouriteTeams.map { team ->
                    fixturesByTeam(team.id).map { fixtures ->
                        team to fixtures // Combina el equipo con sus fixtures
                    }
                }) { teamWithFixturesArray ->
                    teamWithFixturesArray.toList()
                }
            }
    }

}


