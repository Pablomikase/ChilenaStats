package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    vm: DashboardViewModel = viewModel()
) {

    val dashboardState = rememberDashboardState()
    dashboardState.uiReadyEffect { vm.onUiReady(1) }

    Screen {
        Scaffold(
            modifier = Modifier.nestedScroll(dashboardState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.dashboard_main_title)) },
                    scrollBehavior = dashboardState.scrollBehavior
                )
            }
        ) {contentPadding ->
            val state by vm.state.collectAsState()
            LazyVerticalGrid(
                contentPadding = contentPadding,
                columns = GridCells.Adaptive(minSize = 150.dp),
            ) {
                items(state.fixtures){ fixture ->
                    Text("Fixture: ${fixture.id}")
                }
            }
        }
    }

}