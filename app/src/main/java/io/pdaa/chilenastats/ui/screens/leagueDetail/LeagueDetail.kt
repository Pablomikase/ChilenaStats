package io.pdaa.chilenastats.ui.screens.leagueDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.ui.common.LoadingIndicator
import io.pdaa.chilenastats.ui.screens.Screen

@Composable
fun LeagueScreen(
    vm: LeagueDetailViewModel,
    onBackPressed: () -> Unit
) {
    val state = vm.state
    Screen {
        Scaffold(
            topBar = {
                LeagueDetailTopBar(
                    title = state.league?.name ?: "",
                    onBack = { onBackPressed() })
            },
            //contentWindowInsets = WindowInsets.safeDrawing
        ) { contentPadding ->
            if (state.isLoading) {
                LoadingIndicator()
            }
            state.league?.let {
                LeagueDetail(modifier = Modifier.padding(contentPadding), league = state.league)
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueDetailTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    )
}


@Composable
fun LeagueDetail(modifier: Modifier = Modifier, league: LeagueUi) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = league.logo,
            contentDescription = league.name,
            modifier = Modifier
                .width(250.dp)
                .aspectRatio(2 / 3f)
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.White)
                .padding(12.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = league.name,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = league.type,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}