package com.merttoptas.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

/**
 * Created by merttoptas on 13.03.2022
 */
@Composable
fun RickAndMortyCharacterShimmer(
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
                .size(100.dp)
                .padding(vertical = 10.dp)
        ) {
            Box(
                Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight()
                    .width(80.dp)
                    .clip(shape = RoundedCornerShape(15))
                    .background(color = Color.LightGray)
            ) {
            }

            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                RickAndMortyText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray),
                    text = "",
                    style = MaterialTheme.typography.body1,
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        modifier = Modifier
                            .size(12.dp),
                        backgroundColor = Color.LightGray,
                        shape = RoundedCornerShape(50)
                    ) {}

                    Spacer(modifier = Modifier.width(5.dp))
                    RickAndMortyText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.LightGray),
                        text = "",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    RickAndMortyCharacterShimmer(
        modifier = Modifier,
    )
}