package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.common.BaseScaffold
import io.pdaa.chilenastats.ui.common.PermissionRequestEffect
import io.pdaa.chilenastats.ui.screens.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionScreen(
    vm: CountrySelectionViewModel,
    onContinueToLeagues: (List<String>) -> Unit
) {
    val countrySelectionState = rememberCountrySelectionState()
    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
        vm.onUiReady()
    }

    Screen {
        val state by vm.state.collectAsState()
        val isTablet = countrySelectionState.isTablet()
        BaseScaffold(
            state = state,
            modifier = Modifier.nestedScroll(countrySelectionState.scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.country_selector_main_title)) },
                    scrollBehavior = countrySelectionState.scrollBehavior
                )
            }
        ) { contentPadding , countriesList->

            Box(modifier = Modifier.fillMaxSize()) {

                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                    columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                    horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = contentPadding
                ) {
                    items(countriesList) { country ->
                        CountryCard(country = country, onCountrySelected = {
                            vm.onCountrySelected(it)
                        })
                    }
                }

                if (vm.isAnyCountrySelected()) Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(countrySelectionState.getSystemBarColor())
                                )
                            )
                        )
                ) {
                    ElevatedButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp),
                        onClick = {
                            onContinueToLeagues(vm.filterSelectedCountries())
                        }) {
                        Text(text = stringResource(R.string.country_selector_continue_to_leagues_button))
                    }
                }
            }
        }
    }
}