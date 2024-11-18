package io.pdaa.chilenastats.ui.screens.onboarding.commonComposables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import io.pdaa.chilenastats.data.models.local.TeamUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> OnboardingCardSelector(
    modifier: Modifier = Modifier,
    elementUi: T,
    onSelectorClicked: (T) -> Unit,
    isSelected: Boolean,
) {
    Box(modifier = modifier.width(132.dp)
        .aspectRatio(2/3f)
        .clip(MaterialTheme.shapes.small)
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .combinedClickable(enabled = true) {
                    onSelectorClicked(elementUi)
                }
                .padding(8.dp)
                .align(Alignment.TopEnd),
            horizontalAlignment = CenterHorizontally,
        ) {
            AsyncImage(
                model = when (elementUi) {
                    is LeagueUi -> elementUi.logo
                    is TeamUi -> elementUi.logo
                    else -> ""
                },
                contentDescription = when (elementUi) {
                    is LeagueUi -> elementUi.name
                    is TeamUi -> elementUi.logo
                    else -> ""
                },
                modifier = Modifier
                    .fillMaxWidth()
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
                text = when (elementUi) {
                    is LeagueUi -> elementUi.name
                    is TeamUi -> elementUi.name
                    else -> ""
                },
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            Text(
                text = when (elementUi) {
                    is LeagueUi -> elementUi.type
                    is TeamUi -> elementUi.country
                    else -> ""
                },
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }

        if(isSelected) Box(
            Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                )
        )
    }
}

@Preview
@Composable
private fun OnboardingCardSelectorPrev() {
    OnboardingCardSelector(
        elementUi = LeagueUi(
            id = 2,
            name = "Premier League - super Leage assdf",
            type = "League",
            logo = "https://media.api-sports.io/football/leagues/39.png",
            isSelected = true
        ),
        onSelectorClicked = {},
        isSelected = true
    )
}