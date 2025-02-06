package io.pdaa.chilenastats.ui.screens.dashboard

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.sampledata.sampleFixtureContainer
import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.FetchFixturesFromFavouriteTeamsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var fetchFixturesFromFavouriteTeamsUseCase: FetchFixturesFromFavouriteTeamsUseCase

    private lateinit var dashboardViewModel: DashboardViewModel

    private val fixturesContainer = sampleFixtureContainer(1,2,3,4,5)
    private val teams = sampleTeams(1,2,3,4,5)
    private val fixturesByTeam = teams.map { it to fixturesContainer }
    @Before
    fun setUp(){
        whenever(fetchFixturesFromFavouriteTeamsUseCase()).thenReturn(flowOf(fixturesByTeam))
        dashboardViewModel = DashboardViewModel(fetchFixturesFromFavouriteTeamsUseCase)
    }


    @Test
    fun `Fixtures are not requested when the ui is not ready`() = runTest {
        dashboardViewModel.newState.test {
            assertEquals(Result.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Fixtures are requested when the ui is ready`() = runTest {
        dashboardViewModel.newState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(fixturesByTeam), awaitItem())
        }
    }



}