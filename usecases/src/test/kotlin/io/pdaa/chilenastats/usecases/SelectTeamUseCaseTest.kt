package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.sampledata.sampleTeam
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SelectTeamUseCaseTest{

    @Test
    fun `Invoke calls repository`(): Unit= runBlocking {
        val sampleTeam = sampleTeam(1)
        val repository = mock<TeamRepository>()
        val useCase = SelectTeamUseCase(repository)

        useCase(sampleTeam)

        verify(repository).selectTeam(sampleTeam)

    }
}