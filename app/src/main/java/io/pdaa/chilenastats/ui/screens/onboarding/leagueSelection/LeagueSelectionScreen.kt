package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
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
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.common.LoadingIndicator
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.OnboardingCardSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueSelectionScreen(
    onContinueToTeamSelection: (
        leagues: List<Int>
    ) -> Unit,
    onSkipAndGoToDashboard: () -> Unit,
    selectedCountries: List<String>,
    vm: LeaguesViewModel = viewModel()
) {

    val leagueSelectionState = rememberLeagueSelectionState()
    leagueSelectionState.UiReadyToFetchData(
        execute = { vm.onUiReady(it) },
        regionCodes = selectedCountries
    )
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.leagues_selection_main_title)) },
                    scrollBehavior = leagueSelectionState.scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(leagueSelectionState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { contentPadding ->
            val screenState by vm.state.collectAsState()
            val isTablet = leagueSelectionState.isTablet()

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
                    items(screenState.leagues) { item ->
                        OnboardingCardSelector(
                            elementUi = item,
                            onSelectorClicked = { vm.onLeagueSelected(it) },
                            isSelected = item.isSelected
                        )
                    }
                }

                if(vm.isAnyLeaguesSelected()) Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(leagueSelectionState.getSystemBarColor())
                                )
                            )
                        )
                ) {
                    ElevatedButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            onContinueToTeamSelection(
                                vm.filterSelectedLeagues()
                            )
                        }) {
                        Text(text = stringResource(R.string.leagues_selector_continue_to_teams_button))
                    }
                }


            }

        }
    }
}


