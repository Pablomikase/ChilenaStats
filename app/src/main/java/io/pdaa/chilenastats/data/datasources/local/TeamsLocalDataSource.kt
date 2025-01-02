package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.framework.models.database.TeamDB
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.flow.Flow

interface TeamsLocalDataSource {
    val teams: Flow<List<TeamDB>>
    val favoriteTeams: Flow<List<TeamUi>>
    val  isEmpty: Flow<Boolean>
    suspend fun insertTeams(teams: List<TeamUi>)
}