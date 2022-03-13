package com.merttoptas.composebase.features.screen.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.merttoptas.composebase.features.component.RickAndMortyCharactersCard
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyTopBar

/**
 * Created by merttoptas on 13.03.2022
 */

@Composable
fun CharactersScreen(
    navController: NavController,
    viewModel: CharactersViewModel
) {

    val scaffoldState = rememberScaffoldState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = "Characters",
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
private fun Content(viewModel: CharactersViewModel, navController: NavController) {
    val viewState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = android.R.color.background_light))
            .padding(horizontal = 15.dp),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            itemsIndexed(items = viewState.data ?: listOf()) { index, item ->
                RickAndMortyCharactersCard(
                    modifier = Modifier,
                    id = item.id.toLong(),
                    name = item.name,
                    status = item.status ?: Status.Unknown,
                    type = item.species,
                    imageUrl = item.image,
                    isLiked = false,
                    detailClick = {
                    },
                    likeClick = {}
                )
            }
        }
    }
}