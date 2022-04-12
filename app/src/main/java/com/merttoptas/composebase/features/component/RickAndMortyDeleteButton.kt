package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by merttoptas on 30.03.2022
 */

@Composable
fun RickAndMortyDeleteButton(
    onClick: () -> Unit,
) {

    IconButton(onClick = {
        onClick()
    }) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = rememberVectorPainter(Icons.Default.Delete),
            contentDescription = null,
            tint = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    RickAndMortyDeleteButton(
        onClick = {}
    )
}