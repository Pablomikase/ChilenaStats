package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.domain.LeagueUi
import kotlinx.coroutines.flow.Flow

interface LeaguesLocalDataSource {
    val leagues: Flow<List<LeagueUi>>

    suspend fun insertLeagues(leagues: List<LeagueUi>)
}

