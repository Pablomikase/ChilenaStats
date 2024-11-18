package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import io.pdaa.chilenastats.ui.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
class TeamSelectionState(val scrollBehavior: TopAppBarScrollBehavior) {

    @Composable
    fun isTablet(): Boolean {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp >= Constants.MOBILE_LIMIT_SIZE
    }

    @Composable
    fun UiReadyToFetchData(
        execute: (countries: List<String>, leagueIds: List<Int>) -> Unit,
        countries: List<String>,
        leagueIds: List<Int>
    ) {
        LaunchedEffect(Unit) {
            execute(countries, leagueIds)
        }
    }

    @Composable
    fun getSystemBarColor(): Int{
        val context = LocalContext.current
        val window = (context as Activity).window
        return window.navigationBarColor
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberTeamSelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember {
    TeamSelectionState(scrollBehavior)
}