package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.ui.common.PROGRESS_INDICATOR_TAG
import org.junit.Rule
import org.junit.Test


class DashboardScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgressIndicator() : Unit = with(composeTestRule){
        setContent {
            DashboardScreen(
                state = Result.Loading
            )
        }
        onNodeWithTag(PROGRESS_INDICATOR_TAG).assertExists()

    }

    @Test
    fun whenErrorState_showErrorIndicator() : Unit = with(composeTestRule){
        setContent {
            DashboardScreen(
                state = Result.Error(Exception())
            )
        }
        val errorText = InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.error)
        onNodeWithText(errorText, ignoreCase = true).assertExists()
    }
}