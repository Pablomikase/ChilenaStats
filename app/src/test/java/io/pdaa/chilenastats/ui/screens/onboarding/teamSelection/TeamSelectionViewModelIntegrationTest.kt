package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.fakes.buildTeamsRepositoryWith
import io.pdaa.chilenastats.sampledata.sampleCountries
import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.teams.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TeamSelectionViewModelIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private lateinit var teamSelectionViewModel: TeamSelectionViewModel


    @Test
    fun `Teams are not requested when the ui is not ready`() = runTest {
        teamSelectionViewModel = buildViewModelWith()
        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `Leagues are requested when the ui is ready`() = runTest{
        val localTeams = sampleTeams(1,2,3,4)
        teamSelectionViewModel = buildViewModelWith(localData = localTeams)
        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localTeams), awaitItem())
        }
    }

    @Test
    fun `Teams are requested from remote server`() = runTest{
        val remoteTeams = sampleTeams(1,2,3,4)
        val remoteCountries = sampleCountries(1,2,3,4)
        val teamSelectionViewModel = buildViewModelWith(
            remoteData = remoteTeams,
            remoteCountriesData = remoteCountries
        )

        teamSelectionViewModel.teamsState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(remoteTeams), awaitItem())
        }

    }
}

private fun buildViewModelWith(
    localData: List<TeamUi> = emptyList(),
    remoteData: List<TeamUi> = emptyList(),
    localCountriesData: List<CountryUi> = emptyList(),
    remoteCountriesData: List<CountryUi> = emptyList()
): TeamSelectionViewModel {
    val teamsRepository =
        buildTeamsRepositoryWith(localData, remoteData, localCountriesData, remoteCountriesData)
    val fetchTeamsUseCase = FetchTeamsUseCase(teamsRepository)
    val selectTeamUseCase = SelectTeamUseCase(teamsRepository)
    return TeamSelectionViewModel(fetchTeamsUseCase, selectTeamUseCase)
}