package io.pdaa.chilenastats.ui.screens.onboarding.commonComposables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.pdaa.chilenastats.data.models.local.LeagueUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingCardSelector(modifier: Modifier = Modifier, league: LeagueUi, onSelectorClick: (LeagueUi) -> Unit) {
    Column(
        modifier = modifier
            .width(120.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        AsyncImage(
            model = league.logo,
            contentDescription = league.name,
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(enabled = true) {
                    onSelectorClick(league)
                }
                .padding(2.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )

                .background(Color.White)
                .padding(8.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = league.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = league.type,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun OnboardingCardSelectorPrev() {
    OnboardingCardSelector(league = LeagueUi(1,"name", "type","")) { }
}