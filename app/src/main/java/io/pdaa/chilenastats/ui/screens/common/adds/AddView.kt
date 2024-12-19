package io.pdaa.chilenastats.ui.screens.common.adds

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdView(
    addUnitIdentifier: String,
) {
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