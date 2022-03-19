package com.merttoptas.composebase.features.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortyFloatingActionBar(
    navController: NavController,
) {
    var isClick by remember { mutableStateOf(false) }
    val animatedColor by animateColorAsState(targetValue = if (isClick) Color.Green else Color.White)
    val infiniteTransition = rememberInfiniteTransition()

    LaunchedEffect(isClick) {
        if (isClick) {
            delay(800)
            isClick = false
        }
    }

    val favoriteSize by infiniteTransition.animateFloat(
        initialValue = 24.0f,
        targetValue = 48.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                delayMillis = 100,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    FloatingActionButton(
        onClick = { isClick = true },
        contentColor = Color.Transparent,
        backgroundColor = animatedColor
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(if (isClick) favoriteSize.dp else 24.dp)
        )
    }
}
