package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.screens.Screen

@Composable
fun CountrySelectorScreen(
    onContinueToLeagues: () -> Unit
) {
    Screen {
        Scaffold { contentPadding ->
            Box(modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()) {
                TextButton(onClick = { onContinueToLeagues() }) {
                    Text(stringResource(R.string.select_leagues))
                }
            }
        }
    }
}