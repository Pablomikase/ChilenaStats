package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.repositories.helper.sampleLeagues
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class LeaguesRepositoryTest{

    @Mock
    lateinit var leaguesRemoteDataSource: LeaguesRemoteDataSource
    @Mock
    lateinit var leaguesLocalDataSource: LeaguesLocalDataSource

    private lateinit var leaguesRepository: LeaguesRepository

    @Before
    fun setUp(){
        leaguesRepository = LeaguesRepository(leaguesRemoteDataSource, leaguesLocalDataSource)
    }

    @Test
    fun `Fetch leagues from local data source when available`() = runBlocking{

        val leaguesFlow = flowOf(sampleLeagues(1,2,3))

        whenever(leaguesLocalDataSource.leagues).thenReturn(leaguesFlow)

        val result = leaguesRepository.leagues

        assertEquals(leaguesFlow.first(), result.first())


    }
}