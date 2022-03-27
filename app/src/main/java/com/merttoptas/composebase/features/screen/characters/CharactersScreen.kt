package com.merttoptas.composebase.features.screen.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.component.RickAndMortyCharacterShimmer
import com.merttoptas.composebase.features.component.RickAndMortyCharactersCard
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyTopBar
import com.merttoptas.composebase.features.navigation.NavScreen
import com.merttoptas.composebase.utils.Utility.rememberFlowWithLifecycle

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
        content = { Content(viewModel, navController) },
        backgroundColor = MaterialTheme.colors.background
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(viewModel: CharactersViewModel, navController: NavController) {
    val viewState by viewModel.uiState.collectAsState()
    var pagingItems: LazyPagingItems<CharacterDto>? = null
    viewState.pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

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
            if (viewState.isLoading) {
                items(10) {
                    RickAndMortyCharacterShimmer()
                }
            } else {
                pagingItems?.let {
                    itemsIndexed(items = it) { index, item ->
                        RickAndMortyCharactersCard(
                            id = item?.id?.toLong() ?: 0,
                            name = item?.name ?: "",
                            status = item?.status ?: Status.Unknown,
                            type = item?.species ?: "",
                            imageUrl = item?.image,
                            isLiked = false,
                            detailClick = {
                                //   navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${item.convertToJSON()}"))
                            },
                            likeClick = {}
                        )
                    }
                }
            }
        }
    }
}