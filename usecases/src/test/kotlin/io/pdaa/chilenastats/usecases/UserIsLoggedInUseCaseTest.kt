package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.data.repositories.TeamRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class UserIsLoggedInUseCaseTest {
    @Test
    fun `Invoke calls repository - teams repository is empty - user is logged in`() = runBlocking {
        val useCase = UserIsLoggedInUseCase(mock {
            on { areLocalTeamsEmpty } doReturn flowOf(false)
        })
        val result = useCase().toList()
        assertEquals(listOf(true), result)
    }

    @Test
    fun `Invoke calls repository - teams repository has data - user is not logged in`() = runBlocking {
        val useCase = UserIsLoggedInUseCase(mock {
            on { areLocalTeamsEmpty } doReturn flowOf(true)
        })
        val result = useCase().toList()
        assertEquals(listOf(false), result)
    }


}