package com.merttoptas.composebase.features.screen.episodes

import android.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.features.navigation.NavScreen

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background_light))
            .padding(horizontal = 15.dp),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (viewState.isLoading) {
                items(10) {
                    RickAndMortyEpisodesShimmer()
                }
            } else {
                items(items = viewState.data ?: listOf()) { item ->
                    RickAndMortyEpisodesCard(
                        name = item.name ?: "",
                        date = item.airDate ?: "",
                        episode = item.episode ?: "",
                        detailClick = {
                            // navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${item.convertToJSON()}"))
                        },
                        )
                }
            }
        }
    }
}