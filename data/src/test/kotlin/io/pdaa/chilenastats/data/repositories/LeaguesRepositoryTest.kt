package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.sampledata.sampleLeague
import io.pdaa.chilenastats.sampledata.sampleLeagues
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class LeaguesRepositoryTest {

    @Mock
    lateinit var leaguesRemoteDataSource: LeaguesRemoteDataSource

    @Mock
    lateinit var leaguesLocalDataSource: LeaguesLocalDataSource

    private lateinit var leaguesRepository: LeaguesRepository

    private val remoteLeagues = sampleLeagues(1, 2, 3)
    private val localLeagues = sampleLeagues(4, 5, 6)

    @Before
    fun setUp() {
        leaguesRepository = LeaguesRepository(leaguesRemoteDataSource, leaguesLocalDataSource)
    }

    @Test
    fun `Fetch leagues from local data source when available`() = runBlocking {

        val leaguesFlow = flowOf(localLeagues)

        whenever(leaguesLocalDataSource.leagues).thenReturn(leaguesFlow)

        val result = leaguesRepository.leagues

        assertEquals(leaguesFlow.first(), result.first())
    }

    @Test
    fun `Save remote leagues when local is empty`(): Unit = runBlocking {
        val localLeagues = flowOf<List<LeagueUi>>(emptyList())
        whenever(leaguesLocalDataSource.leagues).thenReturn(localLeagues)
        whenever(leaguesRemoteDataSource.fetchLeagues()).thenReturn(remoteLeagues)
        leaguesRepository.leagues.first()
        verify(leaguesLocalDataSource).insertLeagues(remoteLeagues)
    }

    @Test
    fun `Select and unselect a league`(): Unit = runBlocking {
        val sampleLeague = sampleLeague(1)

        leaguesRepository.selectLeague(sampleLeague)

        verify(leaguesLocalDataSource).insertLeagues(listOf(sampleLeague.copy(isFavourite = sampleLeague.isFavourite.not())))

    }
}