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
import io.pdaa.chilenastats.ui.common.BaseScaffold
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.OnboardingCardSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueSelectionScreen(
    onContinueToTeamSelection: () -> Unit,
    onContinueToDashboard:() -> Unit,
    vm: LeaguesViewModel
) {

    val leagueSelectionState = rememberLeagueSelectionState()
    leagueSelectionState.UiReadyToFetchData(
        execute = { vm.onUiReady() }
    )
    Screen {
        val screenState by vm.state.collectAsState()
        val isTablet = leagueSelectionState.isTablet()
        BaseScaffold(
            state = screenState,
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.leagues_selection_main_title)) },
                    scrollBehavior = leagueSelectionState.scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(leagueSelectionState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { contentPadding, leagues ->

            Box {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                    columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                    horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = contentPadding
                ) {
                    items(leagues) { league ->
                        OnboardingCardSelector(

                            onSelectorClicked = { vm.onLeagueSelected(league) },
                            isSelected = league.isFavourite,
                            imageUrl = league.logo,
                            title = league.name,
                            subtitle = league.type
                        )
                    }
                }

                if (vm.isAnyLeaguesSelected()) Column(
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
                            onContinueToTeamSelection()
                        }) {
                        Text(text = stringResource(R.string.leagues_selector_continue_to_teams_button))
                    }
                }


            }

        }
    }
}


