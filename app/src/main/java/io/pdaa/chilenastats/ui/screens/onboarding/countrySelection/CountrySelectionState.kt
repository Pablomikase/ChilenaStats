package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import android.Manifest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import io.pdaa.chilenastats.ui.common.Constants
import io.pdaa.chilenastats.ui.common.PermissionRequestEffect
import io.pdaa.chilenastats.ui.common.getRegion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class CountrySelectionState(
    val scrollBehavior: TopAppBarScrollBehavior
) {

    @Composable
    fun AskRegionEffect(onRegionSelected: (String) -> Unit) {
        val coroutineContext = rememberCoroutineScope()
        val context = LocalContext.current.applicationContext
        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
            coroutineContext.launch {
                val region = if (granted) context.getRegion() else Constants.DEFAULT_REGION
                onRegionSelected(region)
            }
        }
    }

    @Composable
    fun isTablet(): Boolean {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp >= Constants.MOBILE_LIMIT_SIZE
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberCountrySelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember { CountrySelectionState(scrollBehavior) }