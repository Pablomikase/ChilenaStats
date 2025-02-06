package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.sampledata.sampleTeams
import io.pdaa.chilenastats.ui.common.PROGRESS_INDICATOR_TAG
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test


class TeamSelectionScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgressIndicator(): Unit = with(composeTestRule) {
        setContent {
            TeamSelectionScreen(
                screenState = Result.Loading,
                onSkipAndGoToDashboard = {},
                onTeamSelected = {},
                isAnyTeamSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }
        onNodeWithTag(PROGRESS_INDICATOR_TAG).assertExists()
    }


    @Test
    fun whenErrorState_showErrorIndicator(): Unit = with(composeTestRule) {
        setContent {
            TeamSelectionScreen(
                screenState = Result.Error(Exception()),
                onSkipAndGoToDashboard = {},
                onTeamSelected = {},
                isAnyTeamSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }
        val errorText =
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.error)
        onNodeWithText(errorText, ignoreCase = true).assertExists()

    }

    @Test
    fun whenSuccessState_ListOfTeamsAreDisplayed(): Unit = with(composeTestRule) {

        val sampleTeams = sampleTeams(1, 2, 3, 4, 5)
        setContent {
            TeamSelectionScreen(
                screenState = Result.Success(sampleTeams),
                onSkipAndGoToDashboard = {},
                onTeamSelected = {},
                isAnyTeamSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }
        onNodeWithText(sampleTeams[0].name, ignoreCase = true).assertExists()
    }

    @Test
    fun whenATeamIsSelected_ListenerIsCalled(): Unit = with(composeTestRule) {
        val sampleTeams = sampleTeams(1, 2, 3, 4, 5).map { it.copy(isFavourite = false) }
        var selectedTeam = -1
        setContent {
            TeamSelectionScreen(
                screenState = Result.Success(sampleTeams),
                onSkipAndGoToDashboard = {},
                onTeamSelected = { selectedTeam = it.id },
                isAnyTeamSelected = false,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }

        onNodeWithText(sampleTeams.first().name, ignoreCase = true).performClick()
        assertEquals(1, selectedTeam)
    }

    @Test
    fun whenAnyLeagueIsSelected_ContinueToDashboardButton(): Unit = with(composeTestRule) {
        val sampleTeams = sampleTeams(1, 2, 3, 4, 5)
        setContent {
            TeamSelectionScreen(
                screenState = Result.Success(sampleTeams),
                onSkipAndGoToDashboard = {},
                onTeamSelected = {},
                isAnyTeamSelected = true,
                searchBarState = "",
                onSearchBarStateChanged = {},
            )
        }
        val continueButtonText =
            InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.onboarding_skip_button)
        onNodeWithText(continueButtonText).assertExists()
    }

    @Test
    fun whenContinueToOnboardingIsSelected_ContinueToDashboardButtonIsCalled(): Unit =
        with(composeTestRule) {
            val sampleTeams = sampleTeams(1, 2, 3, 4, 5)
            var isButtonCalled = false
            setContent {
                TeamSelectionScreen(
                    screenState = Result.Success(sampleTeams),
                    onSkipAndGoToDashboard = { isButtonCalled = true },
                    onTeamSelected = {},
                    isAnyTeamSelected = true,
                    searchBarState = "",
                    onSearchBarStateChanged = {},
                )
            }
            val continueButtonText =
                InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.onboarding_skip_button)
            onNodeWithText(continueButtonText).performClick()
            assertEquals(true, isButtonCalled)
        }
}