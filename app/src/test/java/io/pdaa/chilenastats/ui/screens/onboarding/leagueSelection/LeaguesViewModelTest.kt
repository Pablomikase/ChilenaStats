package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.sampledata.sampleLeagues
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
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

    private lateinit var leaguesViewModel: LeaguesViewModel

    private val leagues = sampleLeagues(1,2,3,4,5)

    @Before
    fun setUp() {
        whenever(fetchLeaguesUseCase()).thenReturn(flowOf(leagues))
        leaguesViewModel = LeaguesViewModel(
            fetchLeaguesUseCase, selectLeagueUseCase
        )
    }


    @Test
    fun `Leagues are not requested when the ui is not ready`() = runTest {
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Leagues are requested when the ui is ready`() = runTest {

        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leagues), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `League is selected as favourite when clicked`() = runTest {
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leagues), awaitItem())

            leaguesViewModel.onLeagueSelected(leagues[0])
            runCurrent()

            verify(selectLeagueUseCase).invoke(leagues[0])
        }
    }

    @Test
    fun `The method isAnyLeagueSelected returns true when there is a league selected`() = runTest {
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leagues), awaitItem())

            //Pair leagues generated are set as favourite
            assertEquals(true, leaguesViewModel.isAnyLeagueSelected.first())
        }

    }

    /*@OptIn(ExperimentalCoroutinesApi::class)
    @Test*/
    fun `The method isAnyLeagueSelected returns false when there is not any league selected`() = runTest {
        val leaguesUnselected = sampleLeagues(1,2,3,4,5).map { it.copy(isFavourite = false) }
        whenever(fetchLeaguesUseCase()).thenReturn(flowOf(leaguesUnselected))
        val unselectedLeaguesViewModel = LeaguesViewModel(
            fetchLeaguesUseCase, selectLeagueUseCase
        )

        unselectedLeaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leaguesUnselected), awaitItem())
            runCurrent()
            assertEquals(false, leaguesViewModel.isAnyLeagueSelected.first())
        }

    }





}