package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.FreeFootballDataClient
import io.pdaa.chilenastats.data.models.local.FixtureUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FixturesRepository {

    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureUi> = withContext(Dispatchers.IO){
        delay(1000)
        List(10){
            FixtureUi(
                id = it,

            )
        }
    }

}