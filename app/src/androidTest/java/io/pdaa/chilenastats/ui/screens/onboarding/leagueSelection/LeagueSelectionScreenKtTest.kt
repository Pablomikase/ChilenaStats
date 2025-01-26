package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.sampledata.sampleLeagues
import io.pdaa.chilenastats.ui.common.PROGRESS_INDICATOR_TAG
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test


class LeagueSelectionScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgressIndicator(): Unit = with(composeTestRule) {
        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {},
                leaguesListState = Result.Loading,
                onLeagueSelected = {},
                isAnyLeaguesSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},

                )
        }
        onNodeWithTag(PROGRESS_INDICATOR_TAG).assertExists()
    }

    @Test
    fun whenErrorState_showErrorIndicator(): Unit = with(composeTestRule) {
        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {},
                leaguesListState = Result.Error(Exception()),
                onLeagueSelected = {},
                isAnyLeaguesSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }
        val errorText =
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.error)
        onNodeWithText(errorText, ignoreCase = true).assertExists()
    }

    @Test
    fun whenSuccessState_LeaguesListAreShown(): Unit = with(composeTestRule) {

        val sampleLeagues = sampleLeagues(1, 2, 3, 4, 5)

        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {},
                leaguesListState = Result.Success(sampleLeagues),
                onLeagueSelected = {},
                isAnyLeaguesSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }

        onNodeWithText(sampleLeagues[0].name, ignoreCase = true).assertExists()
    }

    @Test
    fun whenALeagueIsClicked_ListenerIsCalled(): Unit = with(composeTestRule) {
        val sampleLeagues = sampleLeagues(1, 2, 3, 4, 5).map { it.copy(isFavourite = false) }

        var selectedLeagueId: Int = -1

        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {},
                leaguesListState = Result.Success(sampleLeagues),
                onLeagueSelected = { selectedLeagueId = it.id },
                isAnyLeaguesSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }

        onNodeWithText(sampleLeagues[0].name, ignoreCase = true).performClick()

        assertEquals(1, selectedLeagueId)
    }


    @Test
    fun whenAnyLeagueIsSelected_ContinueToTeamsButtonAppears(): Unit = with(composeTestRule) {
        //each pair league is set as selected
        val sampleLeagues = sampleLeagues(1, 2, 3, 4, 5)

        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {},
                leaguesListState = Result.Success(sampleLeagues),
                onLeagueSelected = {},
                isAnyLeaguesSelected = true,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )

        }
        val continueButtonText =
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.leagues_selector_continue_to_teams_button)
        onNodeWithText(continueButtonText).assertExists()

    }

    @Test
    fun whenContinueToTeamsButtonIsClicked_ListenerIsCalled(): Unit = with(composeTestRule) {
        val sampleLeagues = sampleLeagues(1, 2, 3, 4, 5)
        var isButtonClicked = false
        setContent {
            LeagueSelectionScreen(
                onContinueToTeamSelection = { isButtonClicked = true },
                leaguesListState = Result.Success(sampleLeagues),
                onLeagueSelected = {},
                isAnyLeaguesSelected = true,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }
        val continueButtonText =
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.leagues_selector_continue_to_teams_button)
        onNodeWithText(continueButtonText).performClick()
        assertEquals(true, isButtonClicked)

    }
}