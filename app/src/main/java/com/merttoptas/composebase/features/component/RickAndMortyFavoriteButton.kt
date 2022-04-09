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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.screen.characters.CharactersViewEvent
import com.merttoptas.composebase.features.screen.characters.CharactersViewModel
import com.merttoptas.composebase.features.ui.theme.Gray500
import com.merttoptas.composebase.utils.Utility.getAnimateFloat
import kotlinx.coroutines.delay

/**
 * Created by merttoptas on 27.03.2022
 */
@Composable
fun RickAndMortyFavoriteButton(
    viewModel: CharactersViewModel = hiltViewModel(),
    dto: CharacterDto
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
        viewModel.onTriggerEvent(CharactersViewEvent.UpdateFavorite(dto))
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
