package com.merttoptas.composebase.features.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.merttoptas.composebase.R
import com.merttoptas.composebase.features.ui.theme.ComposeBaseTheme

/**
 * Created by merttoptas on 19.03.2022
 */

@Composable
fun RickAndMortyEpisodesCard(
    modifier: Modifier = Modifier,
    name: String,
    date: String,
    episode: String,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            val (title, textDate, textEpisode, image) = createRefs()
            RickAndMortyText(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
                text = "Episode Name: $name",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            RickAndMortyText(
                modifier = Modifier.constrainAs(textDate) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                },
                text = "Date: $date",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            RickAndMortyText(
                modifier = Modifier.constrainAs(textEpisode) {
                    top.linkTo(textDate.bottom)
                    start.linkTo(parent.start)
                },
                text = "Episode: $episode",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Image(
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                    end.linkTo(parent.end)
                },
                painter = painterResource(id = R.drawable.ic_arrow_right_icon),
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
private fun BodyPreview() {
    ComposeBaseTheme {
        RickAndMortyEpisodesCard(
            modifier = Modifier,
            name = "Rick and Morty",
            date = "2020-03-19",
            episode = "S01E01",
        )
    }
}