package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import app.cash.turbine.test
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.fakes.buildLeaguesRepositoryWith
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.sampledata.sampleLeagues
import io.pdaa.chilenastats.testRules.CoroutineTestRule
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class LeaguesViewModelIntegrationTest{

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private lateinit var leaguesViewModel: LeaguesViewModel

    @Test
    fun `Leagues are not requested when the ui is not ready`() = runTest {
        leaguesViewModel = buildViewModelWith()
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Leagues are requested when the ui is ready from local data source`() = runTest {
        val remoteLeagues = sampleLeagues(1,2,3,4,5)
        leaguesViewModel = buildViewModelWith(localData = remoteLeagues)
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(remoteLeagues), awaitItem())
        }
    }

    @Test
    fun `Leagues are requested from remote data source when local is empty`() = runTest {
        val remoteLeagues = sampleLeagues(1,2,3,4,5)
        leaguesViewModel = buildViewModelWith(localData = emptyList(), remoteData = remoteLeagues)
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(remoteLeagues), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `A League is selected as favourite when clicked`() = runTest {
        val leagues = sampleLeagues(1,2,3,4,5).map { it.copy(isFavourite = false) }
        leaguesViewModel = buildViewModelWith(localData = leagues)
        leaguesViewModel.leaguesState.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(leagues), awaitItem())

            leaguesViewModel.onLeagueSelected(leagues[0])
            runCurrent()
            assertEquals(Result.Success(listOf(leagues.map { it.copy(isFavourite = it == leagues[0]) }
                .first())), awaitItem())

        }
    }

}

private fun buildViewModelWith(
    localData: List<LeagueUi> = emptyList(),
    remoteData: List<LeagueUi> = emptyList()
): LeaguesViewModel {
    val leaguesRepository = buildLeaguesRepositoryWith(localData, remoteData)
    val fetchLeaguesUseCase = FetchLeaguesUseCase(leaguesRepository)
    val selectLeagueUseCase = SelectLeagueUseCase(leaguesRepository)
    return LeaguesViewModel(fetchLeaguesUseCase, selectLeagueUseCase)
}