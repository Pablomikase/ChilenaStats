package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.ui.common.BaseScaffold
import io.pdaa.chilenastats.ui.common.EmptyState
import io.pdaa.chilenastats.ui.common.IndeterminateLinearProgressIndicator
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.OnboardingCardSelector

@Composable
fun LeagueSelectionScreen(onContinueToTeamSelection: () -> Unit, vm: LeaguesViewModel) {

    val state by vm.leaguesState.collectAsState()
    val searchBarState by vm.searchText.collectAsState()
    val isAnyLeaguesSelected by vm.isAnyLeagueSelected.collectAsState(false)
    val isSearching by vm.isSearching.collectAsState()
    LeagueSelectionScreen(
        leaguesListState = state,
        onContinueToTeamSelection = onContinueToTeamSelection,
        onLeagueSelected = vm::onLeagueSelected,
        isAnyLeaguesSelected = isAnyLeaguesSelected,
        searchBarState = searchBarState,
        onSearchBarStateChanged = vm::onSearchBarStateChanged,
        isSearching = isSearching
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueSelectionScreen(
    onContinueToTeamSelection: () -> Unit,
    leaguesListState: Result<List<LeagueUi>>,
    onLeagueSelected: (LeagueUi) -> Unit,
    isAnyLeaguesSelected: Boolean,
    searchBarState: String,
    onSearchBarStateChanged: (String) -> Unit,
    isSearching: Boolean = false
) {

    val leagueSelectionState = rememberLeagueSelectionState()

    Screen {
        val isTablet = leagueSelectionState.isTablet()
        BaseScaffold(
            state = leaguesListState,
            topBar = {

                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.leagues_selection_main_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    scrollBehavior = leagueSelectionState.scrollBehavior
                )

            },
            modifier = Modifier.nestedScroll(leagueSelectionState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        ) { contentPadding, leagues ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                        top = contentPadding.calculateTopPadding(),
                        end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = contentPadding.calculateBottomPadding() + WindowInsets.ime.asPaddingValues()
                            .calculateBottomPadding()
                    )
            ) {


                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    value = searchBarState,
                    onValueChange = onSearchBarStateChanged,
                    label = { Text(stringResource(R.string.leagues_selector_search_label)) },
                    placeholder = { Text(stringResource(R.string.leagues_selector_search_placeholder)) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            stringResource(R.string.search_icon)
                        )
                    }
                )

                IndeterminateLinearProgressIndicator(
                    loading = isSearching,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )


                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (leagues.isEmpty()) {
                        EmptyState(
                            modifier = Modifier
                                .padding(bottom = contentPadding.calculateBottomPadding() + 32.dp)
                                .align(Alignment.Center)
                                .fillMaxSize(),
                            title = stringResource(R.string.empty_state_leagues_title),
                            subtitle = stringResource(id = R.string.empty_state_leagues_subtitle)
                        )
                    }

                    LazyVerticalGrid(
                        modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                        columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                        horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(leagues) { league ->
                            OnboardingCardSelector(

                                onSelectorClicked = { onLeagueSelected(league) },
                                isSelected = league.isFavourite,
                                imageUrl = league.logo,
                                title = league.name,
                                subtitle = league.type
                            )
                        }
                    }

                    if (isAnyLeaguesSelected) Column(
                        modifier = Modifier
                            .padding(bottom = contentPadding.calculateBottomPadding())
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
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 16.dp),
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
}


