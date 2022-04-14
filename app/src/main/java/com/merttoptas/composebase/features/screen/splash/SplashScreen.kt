package com.merttoptas.composebase.features.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToDashboard: (Int) -> Unit
) {
    Content()
    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is SplashViewEvent.DirectToDashBoard -> {
                       navigateToDashboard.invoke(0)
                    }
                }
            }
        }
    }
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Rick And Morty",
            color = Color.White,
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview
@Composable
fun BodyPreview() {
    Content()
}
