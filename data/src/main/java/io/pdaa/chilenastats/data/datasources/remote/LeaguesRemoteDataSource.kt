package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.domain.LeagueUi

interface LeaguesRemoteDataSource {
    suspend fun fetchLeagues(): List<LeagueUi>
}