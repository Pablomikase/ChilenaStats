package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserIsLoggedInUseCase(private val teamsRepository: TeamRepository) {
    operator fun invoke(): Flow<Boolean> = teamsRepository.areLocalTeamsEmpty.map { it.not() }
}