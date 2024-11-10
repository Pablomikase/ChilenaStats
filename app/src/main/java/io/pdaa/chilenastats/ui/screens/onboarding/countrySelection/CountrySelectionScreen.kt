package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.common.Constants
import io.pdaa.chilenastats.ui.common.PermissionRequestEffect
import io.pdaa.chilenastats.ui.common.getRegion
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.PreferencesCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionScreen(
    vm: CountrySelectionViewModel = viewModel(),
    onContinueToLeagues: () -> Unit
) {

    val localContext = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    var region: String

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
        coroutineScope.launch {
            region = if (granted) localContext.getRegion() else Constants.DEFAULT_REGION
            vm.onUiReady(region)
        }
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.country_selector_main_title)) },
                    scrollBehavior = scrollBehavior
                )
            }
        ){ contentPadding ->
            val state = vm.state.collectAsState()

            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxWidth()
            ) {
                TextButton(onClick = { onContinueToLeagues() }) {
                    Text(stringResource(R.string.select_leagues))
                }
            }
            LazyVerticalGrid(
                modifier = Modifier.padding(4.dp),
                columns = GridCells.Adaptive(minSize = 128.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = contentPadding) {
                items(state.value.countries) { country ->
                    PreferencesCard(name = country.name, imgUrl = country.flag ?: "")
                }
            }
        }
    }
}