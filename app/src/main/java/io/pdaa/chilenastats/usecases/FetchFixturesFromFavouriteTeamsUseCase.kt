package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FetchFixturesFromFavouriteTeamsUseCase(
    private val fixturesRepository: FixturesRepository,
    private val teamsRepository: TeamRepository
    ) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<Pair<TeamUi, List<FixtureContainerUi>>>> {

        return teamsRepository.favouriteTeams
            .flatMapLatest { favouriteTeams ->
                if(favouriteTeams.isEmpty()) {
                    return@flatMapLatest flow { emit(emptyList()) }
                }else{
                    combine(favouriteTeams.map { team ->
                        fixturesRepository.fixturesByTeam(team.id).map { teamFixtures ->
                            team to teamFixtures
                        }
                    }) { it.toList() }
                }
            }
    }
}