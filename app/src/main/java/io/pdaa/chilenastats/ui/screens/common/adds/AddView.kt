package io.pdaa.chilenastats.ui.screens.common.adds

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import io.pdaa.chilenastats.R

@Composable
fun BannerAdView(
    addUnitIdentifier: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp)
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)

    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(4.dp)
            ,
            text = stringResource(R.string.add_time_thanks_for_support),
            style = MaterialTheme.typography.bodySmall,
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        ){
            AndroidView(
                factory = {ctx : Context ->
                    AdView(ctx).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = addUnitIdentifier
                        loadAd(AdRequest.Builder().build())
                    }
                },
                update = {
                    it.loadAd(AdRequest.Builder().build())
                },
            )
        }
    }
}

@Preview
@Composable
private fun BannerAddViewPrev() {
    BannerAdView("test")
}