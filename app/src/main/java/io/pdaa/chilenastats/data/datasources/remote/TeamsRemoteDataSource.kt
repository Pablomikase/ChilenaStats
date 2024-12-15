package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.remote.TeamRemoteResponse
import io.pdaa.chilenastats.data.models.remote.asUiModel
import io.pdaa.chilenastats.domain.TeamUi

class TeamsRemoteDataSource {
    suspend fun fetchTeamsByCountryName(countryName: String): List<TeamUi> =
        FreeFootballDataClient.instance.fetchTeamsByCountryName(countryName)
            .response.map { it.asUiModel()}

}

private fun TeamRemoteResponse.asUiModel(): TeamUi = TeamUi(
    id = team.id,
    name = team.name ?: "",
    logo = team.logo ?: "",
    country = team.country ?: "",
    isSelected = false,
    founded = team.founded,
    national = team.national,
    venue = venue.asUiModel()
)