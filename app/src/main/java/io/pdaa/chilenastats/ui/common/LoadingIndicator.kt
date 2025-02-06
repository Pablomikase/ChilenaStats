package io.pdaa.chilenastats.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

const val PROGRESS_INDICATOR_TAG = "progressIndicator"

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(PROGRESS_INDICATOR_TAG),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingIndicatorPreview() {
    LoadingIndicator()
}