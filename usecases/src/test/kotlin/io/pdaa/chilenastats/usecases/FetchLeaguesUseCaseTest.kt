package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.sampledata.sampleLeagues
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchLeaguesUseCaseTest{

    @Test
    fun `Invoke calls repository`() {

        val leaguesFlow = flowOf(sampleLeagues(1,2,3,4,5,6))

        val useCase = FetchLeaguesUseCase(mock{
            on { leagues } doReturn leaguesFlow
        })
        val result = useCase()
        assertEquals(leaguesFlow, result)
    }
}