package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSelectionScreen(modifier: Modifier = Modifier) {

    val teamSelectionState = rememberTeamSelectionState()


    Screen {
        Screen {
            Scaffold(
                modifier = Modifier.nestedScroll(teamSelectionState.scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = {Text(stringResource(R.string.team_selector_main_title))},
                        scrollBehavior = teamSelectionState.scrollBehavior
                    )
                }
            ) { contentPadding ->

            }
        }
    }
}