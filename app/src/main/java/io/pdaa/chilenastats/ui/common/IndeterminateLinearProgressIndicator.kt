package io.pdaa.chilenastats.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun IndeterminateLinearProgressIndicator(
    modifier: Modifier = Modifier,
    loading: Boolean
) {
    LinearProgressIndicator(
        modifier = modifier.fillMaxWidth()
            .graphicsLayer {
                alpha = if(loading) 1f else 0f
            }
        ,
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}