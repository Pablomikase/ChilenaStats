package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow

interface TeamsLocalDataSource {
    val teams: Flow<List<TeamUi>>
    val favoriteTeams: Flow<List<TeamUi>>
    val  isEmpty: Flow<Boolean>
    suspend fun insertTeams(teams: List<TeamUi>)
}