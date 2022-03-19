package com.merttoptas.composebase.features.screen.episodes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyTopBar

/**
 * Created by merttoptas on 19.03.2022
 */

@Composable
fun EpisodesScreen(
    navController: NavController,
    viewModel: EpisodesViewModel
) {
    val scaffoldState = rememberScaffoldState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = "Episodes",
                actions = {
                    IconButton(onClick = {

                    }) {

                    }
                },
                elevation = 10.dp,
            )
        },
        content = { Content(viewModel, navController) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(viewModel: EpisodesViewModel, navController: NavController) {
    val viewState by viewModel.uiState.collectAsState()

    Column() {

    }


}