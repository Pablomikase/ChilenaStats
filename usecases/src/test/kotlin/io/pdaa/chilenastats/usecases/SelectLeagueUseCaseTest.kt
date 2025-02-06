package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.sampledata.sampleLeague
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SelectLeagueUseCaseTest{


    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        val sampleLeague = sampleLeague(1)
        val repository = mock<LeaguesRepository>()
        val useCase = SelectLeagueUseCase(repository)

        useCase(sampleLeague)
        verify(repository).selectLeague(sampleLeague)
    }


}