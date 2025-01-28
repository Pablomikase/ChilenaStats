package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.teams.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
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
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class TeamSelectionViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var fetchTeamsUseCase: FetchTeamsUseCase

    @Mock
    lateinit var selectTeamUseCase: SelectTeamUseCase

    private lateinit var teamSelectionViewModel: TeamSelectionViewModel

    private val teams = sampleTeams(1,2,3,4,5)


    @Before
    fun setUp() {
        whenever(fetchTeamsUseCase()).thenReturn(flowOf(teams))
        teamSelectionViewModel = TeamSelectionViewModel(
            fetchTeamsUseCase, selectTeamUseCase)
    }

    @Test
    fun `Teams are not requested when the ui is not ready`() = runTest {
        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Teams are requested when the ui is ready`() = runTest {
        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(teams), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Team is selected when the user selects a team`() = runTest {
        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(teams), awaitItem())
            teamSelectionViewModel.onTeamSelected(teams[0])
            runCurrent()
            verify(selectTeamUseCase).invoke(teams[0])
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `the method is AnyTeamSelected returns true when there is a team selected`() = runTest {
        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(teams), awaitItem())
            runCurrent()
            //By default the first team is selected in the sample test data
            assertEquals(true, teamSelectionViewModel.isAnyTeamSelected.first())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    //@Test
    fun `the method isAnyTeamSelected returns false when there is no team selected`() = runTest {

        val teamsUnselected = sampleTeams(1,2,3,4,5).map { it.copy(isFavourite = false) }
        whenever(fetchTeamsUseCase()).thenReturn(flowOf(teamsUnselected))
        val unselectedTeamSelectionViewModel = TeamSelectionViewModel(fetchTeamsUseCase, selectTeamUseCase)
        unselectedTeamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(teamsUnselected), awaitItem())
            runCurrent()
            assertEquals(false, teamSelectionViewModel.isAnyTeamSelected.first())
        }
    }





}