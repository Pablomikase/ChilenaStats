package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import io.pdaa.chilenastats.ui.common.BaseStateHolder

@OptIn(ExperimentalMaterial3Api::class)
class TeamSelectionState(val scrollBehavior: TopAppBarScrollBehavior) : BaseStateHolder {


    @Composable
    fun UiReadyToFetchData(
        execute: () -> Unit,
    ) {
        LaunchedEffect(Unit) {
            execute()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberTeamSelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember {
    TeamSelectionState(scrollBehavior)
}