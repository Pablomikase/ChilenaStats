package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.data.models.local.fixture.FixtureResponseUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FixturesRepository(
    private val remoteDataSource: FixturesRemoteDataSource,
) {

    suspend fun fetchFixturesByTeam(teamId: Int): List<FixtureResponseUi> =
        withContext(Dispatchers.IO) {
            remoteDataSource.fetchFixturesByTeam(teamId)
        }
}

