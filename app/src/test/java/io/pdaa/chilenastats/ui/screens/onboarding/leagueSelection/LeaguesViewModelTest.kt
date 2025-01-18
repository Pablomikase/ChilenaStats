package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.sampledata.sampleLeagues
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import io.pdaa.chilenastats.usecases.UserIsLoggedInUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class LeaguesViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var fetchLeaguesUseCase: FetchLeaguesUseCase
    @Mock
    lateinit var selectLeagueUseCase: SelectLeagueUseCase
    @Mock
    lateinit var userIsLoggedInUseCase: UserIsLoggedInUseCase

    private lateinit var leaguesViewModel: LeaguesViewModel

    @Before
    fun setUp() {
        leaguesViewModel = LeaguesViewModel(
            fetchLeaguesUseCase, selectLeagueUseCase, userIsLoggedInUseCase
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Leagues are not requested when the ui is not ready`() = runTest {
        leaguesViewModel.state.first()
        runCurrent()

        verify(fetchLeaguesUseCase, never()).invoke()
    }

    @Test
    fun `Leagues are requested when the ui is ready`() = runTest {

        val leagues = sampleLeagues(1,2,3,4,5)
        whenever(fetchLeaguesUseCase()).thenReturn(flowOf(leagues))
        leaguesViewModel.onUiReady()
        leaguesViewModel.state.first()

        leaguesViewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leagues), awaitItem())
        }
    }






}