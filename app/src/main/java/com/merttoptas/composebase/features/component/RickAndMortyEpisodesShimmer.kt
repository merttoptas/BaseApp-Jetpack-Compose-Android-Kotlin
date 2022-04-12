package com.merttoptas.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

/**
 * Created by merttoptas on 19.03.2022
 */

@Composable
fun RickAndMortyEpisodesShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .shimmer()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                RickAndMortyText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray),
                    text = "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant
                )
                RickAndMortyText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray),
                    text = "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant,
                )

                RickAndMortyText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray),
                    text = "",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant,
                )
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(24.dp)
                    .background(color = Color.LightGray)
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .width(24.dp)
                        .background(color = Color.LightGray)
                ) {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    RickAndMortyEpisodesShimmer(
        modifier = Modifier,
    )
}