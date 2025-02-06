package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource

import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.sampledata.sampleFixtureContainer
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class FixturesRepositoryTest {

    @Mock
    lateinit var fixturesRemoteDataSource: FixturesRemoteDataSource

    @Mock
    lateinit var fixturesLocalDataSource: FixturesLocalDataSource

    @Mock
    lateinit var teamsLocalDataSource: TeamsLocalDataSource

    private lateinit var fixturesRepository: FixturesRepository

    private val localFixtureContainer = sampleFixtureContainer(1, 2, 3, 4, 5)

    private val remoteFixtureContainer = sampleFixtureContainer(6, 7, 8, 9, 10)

    private val localTeams = sampleTeams(1, 2, 3)

    @Before
    fun setUp() {
        fixturesRepository = FixturesRepository(
            remoteDataSource = fixturesRemoteDataSource,
            fixturesLocalDataSource = fixturesLocalDataSource,
            teamsRepository = teamsLocalDataSource
        )
    }


    @Test
    fun `Fetch local fixtures when available`(): Unit = runBlocking {
        whenever(teamsLocalDataSource.favoriteTeams).thenReturn(flowOf(localTeams))
        whenever(fixturesLocalDataSource.getFixturesByTeam(any())).thenReturn(
            flowOf(
                localFixtureContainer
            )
        )

        val result = fixturesRepository.fixturesFromFavouriteTeams().first()

        val expected = localTeams.map {
            it to localFixtureContainer
        }.toList()

        assertEquals(expected, result)
    }

    @Test
    fun `Save remote fixtures when local one is empty`(): Unit = runBlocking {
        whenever(teamsLocalDataSource.favoriteTeams).thenReturn(flowOf(localTeams))
        whenever(fixturesLocalDataSource.getFixturesByTeam(any())).thenReturn(
            flowOf(
                emptyList()
            )
        )
        whenever(fixturesRemoteDataSource.fetchFixturesByTeam(any())).thenReturn(
            remoteFixtureContainer
        )

        fixturesRepository.fixturesFromFavouriteTeams().first()

        localTeams.forEach {
            verify(fixturesLocalDataSource).insertFixtures(remoteFixtureContainer, it.id)
        }
    }
}