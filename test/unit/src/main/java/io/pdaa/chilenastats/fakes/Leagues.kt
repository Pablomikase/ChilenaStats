package io.pdaa.chilenastats.fakes

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.domain.LeagueUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

fun buildLeaguesRepositoryWith(
    localData: List<LeagueUi> = emptyList(),
    remoteData: List<LeagueUi> = emptyList()
): LeaguesRepository {
    val remoteDataSource = FakeLeaguesRemoteDataSource().apply { remoteLeagues = remoteData }
    val localDataSource = FakeLeaguesLocalDataSource().apply { inMemoryLeagues.value = localData }
    return LeaguesRepository(
        remoteDataSource, localDataSource
    )
}

class FakeLeaguesRemoteDataSource : LeaguesRemoteDataSource {

    var remoteLeagues = emptyList<LeagueUi>()

    override suspend fun fetchLeagues(): List<LeagueUi> = remoteLeagues
}

class FakeLeaguesLocalDataSource : LeaguesLocalDataSource {

    val inMemoryLeagues = MutableStateFlow<List<LeagueUi>>(emptyList())

    override val leagues: Flow<List<LeagueUi>>
        get() = inMemoryLeagues

    override suspend fun insertLeagues(leagues: List<LeagueUi>) {
        inMemoryLeagues.value = leagues
    }


}