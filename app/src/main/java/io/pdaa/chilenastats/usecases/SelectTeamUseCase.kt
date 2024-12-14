package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.domain.TeamUi

class SelectTeamUseCase(private val teamsRepository: TeamRepository) {
    suspend operator fun invoke(team: TeamUi) = teamsRepository.selectTeam(team)
}