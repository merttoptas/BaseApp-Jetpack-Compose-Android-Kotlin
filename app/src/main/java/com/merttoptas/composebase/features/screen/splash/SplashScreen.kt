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
import androidx.navigation.NavController
import com.merttoptas.composebase.features.navigation.NavScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 12.03.2022
 */


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {
    Body()
    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is SplashViewEvent.DirectToDashBoard -> {
                        navController.navigate(NavScreen.Dashboard.route) {
                            popUpTo(0)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Body() {
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
    Body()
}
