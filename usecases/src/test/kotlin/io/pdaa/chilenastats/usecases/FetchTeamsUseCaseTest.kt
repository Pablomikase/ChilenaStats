package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.usecases.teams.FetchTeamsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FetchTeamsUseCaseTest{

    @Test
    fun `Invoke calls repository`() {

        val teamsFlow = flowOf(sampleTeams(1,2,3,4,5,6))

        val useCase = FetchTeamsUseCase(mock{
            on { teams } doReturn teamsFlow
        })
        val result = useCase()
        assertEquals(teamsFlow, result)
    }
}