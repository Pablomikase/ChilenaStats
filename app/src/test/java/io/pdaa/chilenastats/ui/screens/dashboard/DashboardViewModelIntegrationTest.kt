package io.pdaa.chilenastats.ui.screens.dashboard

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.fakes.buildFixturesRepositoryWith
import io.pdaa.chilenastats.sampledata.sampleFixtureContainer
import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.FetchFixturesFromFavouriteTeamsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DashboardViewModelIntegrationTest{

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()


    private lateinit var dashboardViewModel: DashboardViewModel

    @Test
    fun `Fixtures are not requested when the ui is not ready`() = runTest {
        dashboardViewModel = buildViewModelWith()
        dashboardViewModel.newState.test {
            assertEquals(Result.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Fixtures are requested when the ui is ready`() = runTest {

        val localTeams = sampleTeams(1)
        val fixturesByTeam = sampleFixtureContainer(1, 2, 3)

        dashboardViewModel = buildViewModelWith(
            localData = fixturesByTeam,
            teams = localTeams
        )
        dashboardViewModel.newState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localTeams.map {
                it to fixturesByTeam
            }), awaitItem())

        }
    }
}

private fun buildViewModelWith(
    localData: List<FixtureContainerUi> = emptyList(),
    remoteData: List<FixtureContainerUi> = emptyList(),
    teams: List<TeamUi> = emptyList()
): DashboardViewModel {
    val fixturesRepository = buildFixturesRepositoryWith(localData, remoteData, teams)
    return DashboardViewModel(
        fetchFixturesFromFavouriteTeamsUseCase = FetchFixturesFromFavouriteTeamsUseCase(fixturesRepository)
    )
}