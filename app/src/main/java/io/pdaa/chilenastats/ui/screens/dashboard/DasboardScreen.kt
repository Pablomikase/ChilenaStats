package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.dashboard.components.FixtureCarouselItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    vm: DashboardViewModel = viewModel(),
    countries: List<String>,
    leagueIds: List<Int>,
    teamIds: List<Int>
) {

    val dashboardState = rememberDashboardState()
    dashboardState.uiReadyEffect { vm.onUiReady(150) }

    Screen {
        Scaffold(
            modifier = Modifier.nestedScroll(dashboardState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.dashboard_main_title)) },
                    scrollBehavior = dashboardState.scrollBehavior
                )
            }
        ) { contentPadding ->
            val state by vm.state.collectAsState()

            Column(modifier = Modifier.padding(paddingValues = contentPadding)) {
                val carouselState = rememberCarouselState { state.fixtures.size }
                HorizontalMultiBrowseCarousel(
                    state = carouselState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(221.dp),
                    preferredItemWidth = 250.dp,
                    itemSpacing = 8.dp,
                    contentPadding = PaddingValues(16.dp)
                ) { itemIndex: Int ->
                    FixtureCarouselItem(
                        modifier = Modifier
                            .height(205.dp)
                            .fillMaxWidth()
                            .maskClip(
                                MaterialTheme.shapes.extraLarge
                            ),
                        fixtureResponseUi = state.fixtures[itemIndex]
                    )

                }
            }
        }
    }

}