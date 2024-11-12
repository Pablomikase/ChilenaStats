package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import android.app.Activity
import android.os.Build
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelectionScreen(
    vm: CountrySelectionViewModel = viewModel(),
    onContinueToLeagues: () -> Unit
) {
    val countrySelectionState = rememberCountrySelectionState()
    countrySelectionState.AskRegionEffect { vm.onUiReady(it) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val systemBarColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val context = LocalContext.current
        val window = (context as Activity).window
        window.navigationBarColor
    } else {
        Color.Black.toArgb() // Fallback color for older versions
    }

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

            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = if (isTablet) 16.dp else 8.dp),
                    columns = GridCells.Adaptive(minSize = if (isTablet) 150.dp else 120.dp),
                    horizontalArrangement = Arrangement.spacedBy(if (isTablet) 16.dp else 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = contentPadding
                ) {
                    items(state.value.countries) { country ->
                        CountryCard(country = country, onCountrySelected = {
                            vm.onCountrySelected(it)
                        })
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(systemBarColor)
                            )
                        ))
                ) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            showBottomSheet = true
                        }) {
                        Text("Show Bottom Sheet")
                    }
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
                            showBottomSheet = true
                        }) {
                        Text("Show Bottom Sheet2")
                    }
                }
            }
        }
    }
}