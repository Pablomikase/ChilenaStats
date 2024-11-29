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
import androidx.compose.material3.Scaffold
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
import io.pdaa.chilenastats.ui.common.LoadingIndicator
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.TeamSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSelectionScreen(
    countries: List<String>,
    leagueIds: List<Int>,
    vm: TeamSelectionViewModel,
    onSkipAndGoToDashboard: (Int) -> Unit
) {

    val teamSelectionState = rememberTeamSelectionState()
    teamSelectionState.UiReadyToFetchData(
        execute = { selectedCountries, selectedLeagueIds ->
            vm.onUiReady(
                selectedCountries,
                selectedLeagueIds
            )
        },
        countries = countries,
        leagueIds = leagueIds
    )
    Screen {
        Screen {
            Scaffold(
                modifier = Modifier.nestedScroll(teamSelectionState.scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.team_selector_main_title)) },
                        scrollBehavior = teamSelectionState.scrollBehavior
                    )
                }
            ) { contentPadding ->
                val screenState by vm.state.collectAsState()
                val isTablet = teamSelectionState.isTablet()

                Box {
                    if (screenState.isLoading) {
                        LoadingIndicator()
                    }

                    LazyVerticalGrid(
                        modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                        columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                        horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = contentPadding
                    ) {
                        items(screenState.teams) { item ->
                            TeamSelector(
                                team = item,
                                onSelectorClicked = { vm.onTeamSelected(it) },
                                isSelected = item.isSelected
                            )

                        }
                    }

                    if (vm.isAnyTeamsSelected()) Column(
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
                                onSkipAndGoToDashboard(vm.getSelectedTeamId())
                            }) {
                            Text(text = stringResource(R.string.onboarding_skip_button))
                        }
                    }

                }

            }
        }
    }
}