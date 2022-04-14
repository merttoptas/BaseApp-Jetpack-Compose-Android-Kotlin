package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.ui.theme.Gray500
import com.merttoptas.composebase.utils.Utility.getAnimateFloat
import kotlinx.coroutines.delay

/**
 * Created by merttoptas on 27.03.2022
 */
@Composable
fun RickAndMortyFavoriteButton(
    dto: CharacterDto,
    onTriggerEvent: (CharacterDto) -> Unit
) {
    var isFavorite by rememberSaveable(dto) { mutableStateOf(dto.isFavorite) }
    var isClick by remember { mutableStateOf(false) }

    LaunchedEffect(isClick) {
        if (isClick) {
            delay(800)
            isClick = false
        }
    }

    IconButton(onClick = {
        isClick = true
        isFavorite = !isFavorite
        dto.isFavorite = isFavorite

//        if (viewModel is CharactersViewModel) {
//            viewModel.onTriggerEvent(CharactersViewEvent.UpdateFavorite(dto))
//        } else if (viewModel is SearchViewModel) {
//            viewModel.onTriggerEvent(SearchViewEvent.UpdateFavorite(dto))
//        }

        onTriggerEvent.invoke(dto)
    }) {
        val tintColor = if (isFavorite) Red else Gray500

        Icon(
            modifier = Modifier.size(if (isClick) getAnimateFloat().value.dp else 24.dp),
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = null,
            tint = tintColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    RickAndMortyFavoriteButton(
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
        onTriggerEvent = {}
    )
}
