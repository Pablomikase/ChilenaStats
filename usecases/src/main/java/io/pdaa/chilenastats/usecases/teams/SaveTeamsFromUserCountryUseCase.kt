package io.pdaa.chilenastats.usecases.teams

import io.pdaa.chilenastats.data.repositories.TeamRepository
import kotlinx.coroutines.CoroutineScope

class SaveTeamsFromUserCountryUseCase(private val teamsRepository: TeamRepository) {

    operator fun invoke(lifecycleScope : CoroutineScope) : Unit = teamsRepository.saveTeamsFromUserCountry(lifecycleScope)

}