package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import io.pdaa.chilenastats.BuildConfig
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.ui.common.BaseScaffold
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.common.adds.BannerAdView
import io.pdaa.chilenastats.ui.screens.common.adds.Logo
import io.pdaa.chilenastats.ui.screens.dashboard.components.FixtureCarouselItem

@Composable
fun DashboardScreen(vm: DashboardViewModel) {
    val newState by vm.newState.collectAsState()
    DashboardScreen(newState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: Result<List<Pair<TeamUi, List<FixtureContainerUi>>>>
) {

    val dashboardState = rememberDashboardState()

    Screen {
        BaseScaffold(
            state = state,
            modifier = Modifier.nestedScroll(dashboardState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Logo() },
                    scrollBehavior = dashboardState.scrollBehavior
                )
            }
        ) { contentPadding, dashboardItems ->

            LazyColumn(
                contentPadding = contentPadding,
            ) {
                items(
                    items = dashboardItems,
                    key = {it.second.hashCode()}
                    ) { teamFixture ->
                    Column {
                        val fixtures = teamFixture.second
                        val carouselState = rememberCarouselState { fixtures.size }
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
                                fixtureResponseUi = fixtures[itemIndex]
                            )

                        }

                        BannerAdView(
                            addUnitIdentifier = BuildConfig.DASHBOARD_BANNER_ID
                        )

                    }
                }
            }
        }
    }
}