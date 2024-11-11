package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen
import io.pdaa.chilenastats.ui.screens.onboarding.commonComposables.PreferencesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionScreen(
    vm: CountrySelectionViewModel = viewModel(),
    onContinueToLeagues: () -> Unit
) {
    val countrySelectionState = rememberCountrySelectionState()
    countrySelectionState.AskRegionEffect { vm.onUiReady(it) }

    Screen {

        Scaffold(
            modifier = Modifier.nestedScroll(countrySelectionState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.country_selector_main_title)) },
                    scrollBehavior = countrySelectionState.scrollBehavior
                )
            }
        ) { contentPadding ->
            val state = vm.state.collectAsState()
            val isTablet = countrySelectionState.isTablet()

            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = contentPadding
            ) {
                items(state.value.countries) { country ->
                    PreferencesCard(name = country.name, imgUrl = country.flag ?: "")
                }
            }


        }
    }
}