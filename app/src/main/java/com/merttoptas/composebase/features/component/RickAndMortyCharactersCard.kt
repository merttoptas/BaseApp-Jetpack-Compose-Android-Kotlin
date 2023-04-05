package com.merttoptas.composebase.features.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto

/**
 * Created by merttoptas on 13.03.2022
 */

@Composable
fun RickAndMortyCharactersCard(
    modifier: Modifier = Modifier,
    status: Status,
    dto: CharacterDto?,
    detailClick: () -> Unit,
    onTriggerEvent: (CharacterDto) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { detailClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                ) {
                    RickAndMortyNetworkImage(
                        imageURL = dto?.image,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(15)),
                        placeholder = R.drawable.ic_place_holder,
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        RickAndMortyText(
                            text = dto?.name.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        RickAndMortyText(
                            text = dto?.species.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Card(
                                modifier = Modifier
                                    .size(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (status == Status.Alive) Color.Green else Color.Red,
                                ),
                                shape = RoundedCornerShape(50)
                            ) {}
                            Spacer(modifier = Modifier.width(5.dp))
                            RickAndMortyText(
                                text = status.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }

                }
            }
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                dto?.let {
                    RickAndMortyFavoriteButton(
                        dto = it,
                        onTriggerEvent = { dto ->
                            onTriggerEvent.invoke(dto)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BodyPreview() {
    RickAndMortyCharactersCard(
        status = Status.Alive,
        detailClick = {},
        onTriggerEvent = {},
        dto = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = Status.Alive,
            type = "Human",
            created = "2017-11-04T18:48:46.250Z",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            gender = "",
            location = null,
            origin = null,
            species = "",
            url = "",
            isFavorite = true
        ),
    )
}