package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.flow.Flow

class FetchFixturesFromFavouriteTeamsUseCase(
    private val fixturesRepository: FixturesRepository,
    ) {
    operator fun invoke(): Flow<List<Pair<TeamUi, List<FixtureContainerUi>>>> = fixturesRepository.fixturesFromFavouriteTeams()
}