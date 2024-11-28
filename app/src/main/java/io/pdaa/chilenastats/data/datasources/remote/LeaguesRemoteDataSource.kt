package io.pdaa.chilenastats.data.datasources.remote

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.database.LeagueDB
import io.pdaa.chilenastats.data.models.remote.asDbModel

class LeaguesRemoteDataSource {

    suspend fun fetchLeagues(): List<LeagueDB> =
        FreeFootballDataClient.instance.fetchLeagues()
            .response.map { it.asDbModel() }

}