package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import io.pdaa.chilenastats.ui.common.BaseStateHolder

@OptIn(ExperimentalMaterial3Api::class)
class LeagueSelectionState(
    val scrollBehavior: TopAppBarScrollBehavior
):BaseStateHolder {

    @Composable
    fun UiReadyToFetchData(execute: (List<String>)-> Unit, regionCodes: List<String>) {
        LaunchedEffect (Unit){
            execute(regionCodes)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberLeagueSelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember {
    LeagueSelectionState(scrollBehavior)}