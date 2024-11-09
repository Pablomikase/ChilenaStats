package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.common.Constants
import io.pdaa.chilenastats.ui.common.PermissionRequestEffect
import io.pdaa.chilenastats.ui.common.getRegion
import io.pdaa.chilenastats.ui.screens.Screen
import kotlinx.coroutines.launch

@Composable
fun CountrySelectionScreen(
    vm: CountrySelectionViewModel = viewModel(),
    onContinueToLeagues: () -> Unit
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
        Scaffold { contentPadding ->
            val state = vm.state.collectAsState()
            LazyColumn {
                items(state.value.countries) { country ->
                    Text(country.name)
                }
            }
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxWidth()
            ) {
                TextButton(onClick = { onContinueToLeagues() }) {
                    Text(stringResource(R.string.select_leagues))
                }
            }
        }
    }
}