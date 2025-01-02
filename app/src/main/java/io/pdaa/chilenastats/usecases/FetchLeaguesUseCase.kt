package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.domain.LeagueUi
import kotlinx.coroutines.flow.Flow


class FetchLeaguesUseCase(private val leaguesRepository: LeaguesRepository) {

    operator fun invoke(): Flow<List<LeagueUi>> = leaguesRepository.leagues

}