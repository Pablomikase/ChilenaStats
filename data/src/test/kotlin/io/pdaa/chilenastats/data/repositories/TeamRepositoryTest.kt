package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.sampledata.sampleCountries
import io.pdaa.chilenastats.sampledata.sampleTeams
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
class TeamRepositoryTest{

    @Mock
    lateinit var teamsRemoteDataSource: TeamsRemoteDataSource

    @Mock
    lateinit var teamsLocalDataSource: TeamsLocalDataSource

    @Mock
    lateinit var regionDataSource: RegionDataSource

    @Mock
    lateinit var countriesLocalDataSource: CountriesLocalDataSource

    @Mock
    lateinit var countriesRemoteDataSource: CountriesRemoteDataSource


    private lateinit var teamRepository: TeamRepository

    private val remoteTeams = sampleTeams(1, 2, 3)
    private val localTeams = sampleTeams(4, 5, 6)

    private val localCountries = sampleCountries(1, 2, 3)

    @Before
    fun setUp(){
        teamRepository = TeamRepository(
            remoteDataSource = teamsRemoteDataSource,
            localDataSource = teamsLocalDataSource,
            regionDataSource = regionDataSource,
            countryLocalDataSource = countriesLocalDataSource,
            countriesRemoteDataSource = countriesRemoteDataSource
        )
    }


    @Test
    fun `Load teams from local data source when available`(): Unit = runBlocking {
        whenever(countriesLocalDataSource.countries).thenReturn(flowOf(localCountries))
        whenever(teamsLocalDataSource.teams).thenReturn(flowOf(localTeams))

        val result = teamRepository.teams.first()

        assertEquals(localTeams, result)
    }

    @Test
    fun `Load remote teams when the local teams are empty and countries are not empty`(): Unit = runBlocking {
        whenever(countriesLocalDataSource.countries).thenReturn(flowOf(localCountries))
        whenever(teamsLocalDataSource.teams).thenReturn(flowOf(emptyList()))
        whenever(teamsRemoteDataSource.fetchTeamsByCountryName(any())).thenReturn(remoteTeams)

        teamRepository.teams.first()

        verify(teamsRemoteDataSource).fetchTeamsByCountryName(localCountries.first().name)
        verify(teamsLocalDataSource).insertTeams(remoteTeams)

    }
}