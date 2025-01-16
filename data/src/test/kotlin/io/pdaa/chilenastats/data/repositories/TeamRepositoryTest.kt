package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
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
    fun `Inserted remote countries if local countries are empty`() {
        whenever(countriesLocalDataSource.countries).thenReturn(flowOf(emptyList()))
    }
}