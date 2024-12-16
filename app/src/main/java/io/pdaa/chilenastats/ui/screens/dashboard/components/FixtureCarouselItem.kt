package io.pdaa.chilenastats.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi

@Composable
fun FixtureCarouselItem(
    modifier: Modifier = Modifier,
    fixtureResponseUi: FixtureContainerUi
    ) {

    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            AsyncImage(
                model = fixtureResponseUi.teams.home.logo,
                contentDescription = fixtureResponseUi.teams.home.name
            )
            AsyncImage(
                model = fixtureResponseUi.teams.away.logo,
                contentDescription = fixtureResponseUi.teams.away.name
            )
        }

        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "${fixtureResponseUi.goals.home} - ${fixtureResponseUi.goals.away}",
            style = MaterialTheme.typography.titleLarge

        )
        Text(text = fixtureResponseUi.fixture.status.long,
            style = MaterialTheme.typography.labelSmall
            )
    }
}

