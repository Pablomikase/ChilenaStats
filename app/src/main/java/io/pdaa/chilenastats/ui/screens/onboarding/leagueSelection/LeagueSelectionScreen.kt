package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import android.Manifest
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.data.models.local.mockLeagues
import io.pdaa.chilenastats.ui.common.Constants
import io.pdaa.chilenastats.ui.common.LoadingIndicator
import io.pdaa.chilenastats.ui.common.PermissionRequestEffect
import io.pdaa.chilenastats.ui.common.getRegion
import io.pdaa.chilenastats.ui.screens.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueSelectionScreen(
    onLeagueClick: (LeagueUi) -> Unit,
    vm: LeaguesViewModel = viewModel()
) {

    val localContext = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    var region = Constants.DEFAULT_REGION

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
        coroutineScope.launch {
            region = if (granted) localContext.getRegion() else Constants.DEFAULT_REGION
            vm.onUiReady(region)
        }
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "${stringResource(R.string.leagues_title)} - $region") },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { contentPadding ->
            val screenState by vm.state.collectAsState()

            if (screenState.isLoading) {
                LoadingIndicator()
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = contentPadding,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                items(screenState.leagues) { item ->
                    LeagueCard(league = item, onLeagueClick = onLeagueClick)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LeagueCard(modifier: Modifier = Modifier, league: LeagueUi, onLeagueClick: (LeagueUi) -> Unit) {
    Column(
        modifier = modifier
            .width(120.dp),
    ) {
        AsyncImage(
            model = league.logo,
            contentDescription = league.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .background(Color.White)
                .padding(12.dp)
                .combinedClickable(enabled = true) {
                    onLeagueClick(league)
                },
            contentScale = ContentScale.Fit,
        )
        Text(
            text = league.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = league.type,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFFF0EAE2
)
@Composable
private fun LeagueCardPreview() {
    LeagueCard(modifier = Modifier, league = mockLeagues[0], {})
}