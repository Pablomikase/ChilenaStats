package io.pdaa.chilenastats.usecases.teams

import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow


/**
 * Use case for fetching the list of teams.
 *
 * This use case is responsible for retrieving the list of teams from the repository checking first if the teams are already cached.
 *
 * @property teamsRepository The repository that provides the team data.
 */
class FetchTeamsUseCase(private val teamsRepository: TeamRepository) {

    /**
     * Invokes the use case to fetch the list of teams.
     *
     * @return A [Flow] emitting the list of [TeamUi] objects.
     */
    operator fun invoke(): Flow<List<TeamUi>> = teamsRepository.teams

}