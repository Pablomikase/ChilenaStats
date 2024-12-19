package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.pdaa.chilenastats.BuildConfig
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.common.BaseScaffold
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.common.adds.BannerAdView
import io.pdaa.chilenastats.ui.screens.dashboard.components.FixtureCarouselItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    vm: DashboardViewModel
) {

    val dashboardState = rememberDashboardState()
    dashboardState.UiReadyToFetchData { vm.onUiReady() }

    Screen {
        val newState by vm.newState.collectAsState()
        BaseScaffold(
            state = newState,
            modifier = Modifier.nestedScroll(dashboardState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.dashboard_main_title)) },
                    scrollBehavior = dashboardState.scrollBehavior
                )
            }
        ) { contentPadding, dashboardItems ->
            //val state by vm.state.collectAsState()

            LazyColumn(
                contentPadding = contentPadding,
            ) {
                items(dashboardItems) { teamFixture ->
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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(122.dp)
                                .padding(16.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colorScheme.inversePrimary)

                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.TopStart)
                                    .padding(4.dp)
                                ,
                                text = stringResource(R.string.add_time_thanks_for_support),
                                style = MaterialTheme.typography.bodySmall,
                            )

                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.Center)
                            ){
                                BannerAdView(
                                    addUnitIdentifier = BuildConfig.DASHBOARD_BANNER_ID/*"ca-app-pub-1318428563885787/3866603174"*/
                                )
                            }

                        }

                    }

                }

            }
        }
    }

}