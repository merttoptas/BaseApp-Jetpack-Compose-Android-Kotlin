package com.merttoptas.composebase.features.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortyFloatingActionBar(
    navController: NavController,
) {
    var isClick by remember { mutableStateOf(false) }
    val rotationalState by animateFloatAsState(targetValue = if (isClick) 180f else 0f)
    val animatedColor by animateColorAsState(targetValue = if (isClick) Color.Green else Color.Red)

    FloatingActionButton(
        onClick = { isClick = !isClick },
        contentColor = Color.Transparent,
        backgroundColor = animatedColor
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.rotate(rotationalState)
        )
    }
}
