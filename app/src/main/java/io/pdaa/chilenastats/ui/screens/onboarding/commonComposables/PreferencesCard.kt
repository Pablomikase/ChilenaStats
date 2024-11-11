package io.pdaa.chilenastats.ui.screens.onboarding.commonComposables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun PreferencesCard(
    modifier: Modifier = Modifier,
    name: String,
    imgUrl: String
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val isTablet = screenWidth >= 600

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = if (!imgUrl.endsWith("svg")) {
                imgUrl
            } else {
                ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            },
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3/2f)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Preview
@Composable
private fun PreferencesCardPrev() {
    PreferencesCard(modifier = Modifier, name = "Name", imgUrl = "")
}