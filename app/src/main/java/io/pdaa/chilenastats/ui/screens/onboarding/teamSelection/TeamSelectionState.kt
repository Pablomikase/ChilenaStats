package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
class TeamSelectionState(val scrollBehavior: TopAppBarScrollBehavior) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberTeamSelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember {
    TeamSelectionState(scrollBehavior)
}