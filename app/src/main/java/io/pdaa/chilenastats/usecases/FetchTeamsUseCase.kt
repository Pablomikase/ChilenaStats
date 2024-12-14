package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow

class FetchTeamsUseCase(private val teamsRepository: TeamRepository) {

    operator fun invoke(): Flow<List<TeamUi>> = teamsRepository.teams

}