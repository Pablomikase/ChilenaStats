package io.pdaa.chilenastats.ui.screens.common.adds

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.pdaa.chilenastats.R
import io.pdaa.chilenastats.ui.theme.LogoFontFamily

@Composable
fun Logo(modifier: Modifier = Modifier) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.inversePrimary)
    )

    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(R.string.dashboard_main_title),
        fontFamily = LogoFontFamily,
        fontSize = 70.sp,
        textAlign = TextAlign.Center,
        style = TextStyle(
            brush = gradientBrush,
            fontWeight = FontWeight.Bold,
            shadow = Shadow(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                offset = Offset(2f, 2f),
                blurRadius = 4f
            )
        )
    )
}

@Preview
@Composable
private fun LogoPreview() {
    Logo()
}