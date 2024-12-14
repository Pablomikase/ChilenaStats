package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import io.pdaa.chilenastats.domain.CountryUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountryCard(
    modifier: Modifier = Modifier,
    country: CountryUi,
    onCountrySelected: (CountryUi) -> Unit
) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 2f)
                .clip(MaterialTheme.shapes.small)
        ) {
            AsyncImage(
                model = if (country.flag?.endsWith("svg") == false) {
                    country.flag
                } else {
                    ImageRequest.Builder(LocalContext.current)
                        .data(country.flag)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                },
                contentDescription = country.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .combinedClickable(enabled = true) {
                        onCountrySelected(country)
                    }
            )
            Checkbox(
                modifier = Modifier.align(Alignment.TopEnd),
                checked = country.isSelected,
                onCheckedChange = { onCountrySelected(country) },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onSurface,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

        }
        Text(
            text = country.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }

}