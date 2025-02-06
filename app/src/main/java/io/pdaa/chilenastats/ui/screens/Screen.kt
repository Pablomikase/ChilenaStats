package io.pdaa.chilenastats.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.pdaa.chilenastats.ui.theme.ChilenaStatsTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    ChilenaStatsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}