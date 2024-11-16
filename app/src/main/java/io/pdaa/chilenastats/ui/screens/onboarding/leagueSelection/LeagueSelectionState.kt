package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import io.pdaa.chilenastats.ui.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
class LeagueSelectionState(
    val scrollBehavior: TopAppBarScrollBehavior
) {

    @Composable
    fun isTablet(): Boolean {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp >= Constants.MOBILE_LIMIT_SIZE
    }

    @Composable
    fun getSystemBarColor(): Int{
        val context = LocalContext.current
        val window = (context as Activity).window
        return window.navigationBarColor
    }

    @Composable
    fun UiReadyToFetchData(onRegion: (String)-> Unit, regionCode: String) {
        onRegion(regionCode)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberLeagueSelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember {
    LeagueSelectionState(scrollBehavior)}