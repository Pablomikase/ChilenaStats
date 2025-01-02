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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingCardSelector(
    modifier: Modifier = Modifier,
    imageUrl:String,
    title: String,
    subtitle: String,
    onSelectorClicked: () -> Unit,
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
                    onSelectorClicked()
                }
                .padding(8.dp)
                .align(Alignment.TopEnd),
            horizontalAlignment = CenterHorizontally,
        ) {
            val imageShape = RoundedCornerShape(8.dp)
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .aspectRatio(1f)
                    .clip(imageShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = imageShape
                    )

                    .background(Color.White)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            Text(
                text = subtitle,
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