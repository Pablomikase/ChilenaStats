package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.domain.LeagueUi


class SelectLeagueUseCase(private val leaguesRepository: LeaguesRepository) {
    suspend operator fun invoke(league: LeagueUi) = leaguesRepository.selectLeague(league)
}