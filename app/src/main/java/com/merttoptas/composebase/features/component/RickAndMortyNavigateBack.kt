package com.merttoptas.composebase.features.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.merttoptas.composebase.R

/**
 * Created by mertcantoptas on 05.04.2023
 */

@Composable
fun RickAndMortyNavigateBack(navigateToBack: () -> Unit) {
    IconButton(onClick = navigateToBack) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}
