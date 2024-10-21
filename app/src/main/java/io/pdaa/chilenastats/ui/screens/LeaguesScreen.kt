package io.pdaa.chilenastats.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.pdaa.chilenastats.data.League
import io.pdaa.chilenastats.data.mockLeagues

@Composable
fun LeaguesScreen(modifier: Modifier = Modifier) {
    Screen {
        Scaffold { contentPadding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = contentPadding,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                items(mockLeagues) { item ->
                    LeagueCard(league = item)
                }
            }
        }
    }
}

@Composable
fun LeagueCard(modifier: Modifier = Modifier, league: League) {
    OutlinedCard(
        modifier = modifier
            .width(120.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        AsyncImage(
            model = league.logo,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Text(
            text = league.type,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = league.type,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}


@Preview
@Composable
private fun LeagueCardPreview() {
    LeagueCard(modifier = Modifier, league = mockLeagues[0])
}