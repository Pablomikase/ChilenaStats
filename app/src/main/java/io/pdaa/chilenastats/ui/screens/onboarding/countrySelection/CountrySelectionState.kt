package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.pdaa.chilenastats.ui.common.BaseStateHolder

@OptIn(ExperimentalMaterial3Api::class)
class CountrySelectionState(
    val scrollBehavior: TopAppBarScrollBehavior
) : BaseStateHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberCountrySelectionState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
) = remember { CountrySelectionState(scrollBehavior) }