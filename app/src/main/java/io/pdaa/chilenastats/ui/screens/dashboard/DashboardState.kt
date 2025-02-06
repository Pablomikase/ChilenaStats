package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
class DashboardState(
    val scrollBehavior: TopAppBarScrollBehavior,
    val screenScrollState: ScrollState,
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDashboardState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    screenScrollState: ScrollState = rememberScrollState(),

    ) =
    remember {
        DashboardState(
            scrollBehavior = scrollBehavior,
            screenScrollState = screenScrollState,

            )
    }