package io.pdaa.chilenastats.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
    ) {

    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons
                .Default.Search,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(1f).height(200.dp),
            tint = Color.Gray
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

    }


}

@Preview
@Composable
private fun EmptyStatePrev() {
    EmptyState(title = "No hay datos", subtitle = "No se encontraron datos")
}