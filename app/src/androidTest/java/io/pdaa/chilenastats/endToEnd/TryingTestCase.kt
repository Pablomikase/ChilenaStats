package io.pdaa.chilenastats.endToEnd

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.di.appModule
import io.pdaa.chilenastats.di.dataSourceModule
import io.pdaa.chilenastats.di.repositoryModule
import io.pdaa.chilenastats.di.useCaseModule
import io.pdaa.chilenastats.di.viewModelModule
import io.pdaa.chilenastats.ui.common.PROGRESS_INDICATOR_TAG
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class TryingTestCase {

    private val instrumentedTestModule = module {
        /*factory<Something> { FakeSomething() }*/
    }

    private val productionModule = module {
        includes(appModule, dataSourceModule, repositoryModule, useCaseModule, viewModelModule)
    }

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule(
        modules = listOf(productionModule, instrumentedTestModule)
    )

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Test
    fun whenApplicationStarts_userSeesLeagues() : Unit = with(composeTestRule) {
        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {},
                leaguesListState = Result.Loading,
                onLeagueSelected = {},
                isAnyLeaguesSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
                isSearching = false,
            )
        }
        onNodeWithTag(PROGRESS_INDICATOR_TAG).assertExists()

    }
}