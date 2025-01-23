package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.ui.common.BaseScaffold
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.TeamSelector

@Composable
fun TeamSelectionScreen(
    vm: TeamSelectionViewModel,
    onSkipAndGoToDashboard: () -> Unit
) {
    val screenState by vm.state.collectAsState()
    TeamSelectionScreen(
        screenState = screenState,
        onSkipAndGoToDashboard = onSkipAndGoToDashboard,
        onTeamSelected = vm::onTeamSelected,
        isAnyTeamSelected = vm::isAnyTeamsSelected
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSelectionScreen(
    screenState: Result<List<TeamUi>>,
    onSkipAndGoToDashboard: () -> Unit,
    onTeamSelected: (TeamUi) -> Unit,
    isAnyTeamSelected: () -> Boolean,
) {

    val teamSelectionState = rememberTeamSelectionState()

    Screen {

        val isTablet = teamSelectionState.isTablet()
        BaseScaffold(
            state = screenState,
            modifier = Modifier.nestedScroll(teamSelectionState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            stringResource(R.string.team_selector_main_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    scrollBehavior = teamSelectionState.scrollBehavior
                )
            }
        ) { contentPadding, teamsList ->

            Box {

                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                    columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                    horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = contentPadding
                ) {
                    items(teamsList) { item ->
                        TeamSelector(
                            team = item,
                            onSelectorClicked = { onTeamSelected(it) },
                            isSelected = item.isSelected
                        )

                    }
                }

                if (isAnyTeamSelected()) Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(teamSelectionState.getSystemBarColor())
                                )
                            )
                        )
                ) {
                    ElevatedButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 8.dp), onClick = {
                            onSkipAndGoToDashboard()
                        }) {
                        Text(text = stringResource(R.string.onboarding_skip_button))
                    }
                }

            }

        }
    }

}