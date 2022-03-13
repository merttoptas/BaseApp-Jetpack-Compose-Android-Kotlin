package com.merttoptas.composebase.features.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status

/**
 * Created by merttoptas on 13.03.2022
 */

@Composable
fun RickAndMortyCharactersCard(
    modifier: Modifier = Modifier,
    id: Long,
    name: String,
    status: Status,
    type: String,
    imageUrl: String?,
    isLiked: Boolean,
    likeClick: (id: Long) -> Unit,
    detailClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { detailClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 10.dp)
        ) {
            Box(Modifier.padding(start = 10.dp)) {
                RickAndMortyNetworkImage(
                    imageURL = imageUrl,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .clip(shape = RoundedCornerShape(15)),
                    placeholder = R.drawable.ic_place_holder,
                    contentScale = ContentScale.Crop,
                )

                /*
                 IconFavorite(
                    isLiked, Modifier
                        .align(Alignment.BottomEnd)
                        .offset(y = (-10).dp, x = (-10).dp)

                ) {
                    likeClick(petId)

                }
                 */


            }

            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                RickAndMortyText(
                    text = name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondaryVariant
                )
                RickAndMortyText(
                    text = type,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondaryVariant,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        modifier = Modifier
                            .size(12.dp),
                        backgroundColor = if (status == Status.Alive) Color.Green else Color.Red,
                        shape = RoundedCornerShape(50)
                    ) {}

                    Spacer(modifier = Modifier.width(5.dp))
                    RickAndMortyText(
                        text = status.name,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant,
                    )
                }
            }
        }
    }
}