package io.pdaa.chilenastats.usecases.teams

import io.pdaa.chilenastats.data.repositories.TeamRepository

class SearchTeamUseCase(private val teamsRepository: TeamRepository)  {

    suspend operator fun invoke(query: String) {
        //must be required from remote all teams with 3 starting query letters
        if (query.length < 3) return
        val remoteQuery = query.take(3)
        teamsRepository.saveTeamsFromSearchRequest(remoteQuery)
    }

}