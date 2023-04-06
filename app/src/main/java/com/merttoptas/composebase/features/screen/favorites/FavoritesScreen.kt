@file:OptIn(ExperimentalMaterial3Api::class)

package com.merttoptas.composebase.features.screen.favorites

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.FavoriteEntity
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.features.screen.charactersdetail.CharactersDetailViewEvent
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 30.03.2022
 */

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    navigateCharacterDetail: (FavoriteEntity) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewState = viewModel.uiState.collectAsState().value
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is FavoritesViewEvent.SnackBarMessage -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(it.message.orEmpty())
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHostState = snackbarHostState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(R.string.favorite_screen_title),
                actions = {
                    if (viewState.isVisibleDelete) {
                        IconButton(onClick = {
                            viewModel.onTriggerEvent(FavoritesViewEvent.OnIsDeleteAllFavoritesChange)
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = rememberVectorPainter(Icons.Default.Delete),
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    }
                },
            )
        },
        content = {
            Content(
                modifier = Modifier.padding(it),
                isLoading = viewState.isLoading,
                favoriteId = viewState.favoriteId,
                isDisplay = viewState.isDisplay,
                isAllDeleteFavorites = viewState.isAllDeleteFavorites,
                favoritesList = viewState.favoritesList,
                triggerEvent = viewModel::onTriggerEvent,
                clickDetail = navigateCharacterDetail::invoke
            )
        },
    )

}

@Composable
private fun Content(
    modifier: Modifier,
    isLoading: Boolean,
    favoriteId: Int?,
    isDisplay: Boolean,
    isAllDeleteFavorites: Boolean,
    favoritesList: List<FavoriteEntity>,
    triggerEvent: (FavoritesViewEvent) -> Unit,
    clickDetail: (FavoriteEntity) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowRickAndMortAlertDialog(
            isDisplay = isDisplay,
            isAllDeleteFavorites = isAllDeleteFavorites,
            triggerEvent = triggerEvent,
            favoriteId = favoriteId
        )

        if (isLoading.not() && favoritesList.isEmpty()) {
            EmptyListAnimation()
        }
        FavoriteList(isLoading, favoritesList, clickDetail, triggerEvent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowRickAndMortAlertDialog(
    isDisplay: Boolean,
    isAllDeleteFavorites: Boolean,
    triggerEvent: (FavoritesViewEvent) -> Unit,
    favoriteId: Int?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        RickAndMortyAlertDialog(
            isDisplayed = isDisplay,
            onClickDelete = {
                if (isAllDeleteFavorites) {
                    triggerEvent.invoke(FavoritesViewEvent.OnDeleteAllFavorites)
                } else {
                    triggerEvent.invoke(FavoritesViewEvent.OnDeleteFavorite)
                }
                triggerEvent.invoke(
                    FavoritesViewEvent.OnDisplayChange(
                        isDisplay = false,
                        favoriteId = favoriteId
                    )
                )
            },
            onBackPressed = {
                triggerEvent.invoke(
                    FavoritesViewEvent.OnDisplayChange(
                        isDisplay = false,
                        favoriteId = favoriteId
                    )
                )
            },
        )
    }
}

@Composable
private fun FavoriteList(
    isLoading: Boolean,
    favoritesList: List<FavoriteEntity>,
    clickDetail: (FavoriteEntity) -> Unit,
    triggerEvent: (FavoritesViewEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (isLoading) {
            items(10) {
                RickAndMortyCharacterShimmer()
            }
        } else if (favoritesList.isNotEmpty()) {
            items(items = favoritesList) { item ->
                RickAndMortyFavoriteRowCard(
                    status = item.status,
                    detailClick = {
                        clickDetail.invoke(item)
                    },
                    dto = item,
                    onDeleteClick = {
                        triggerEvent.invoke(
                            FavoritesViewEvent.OnDisplayChange(
                                isDisplay = true,
                                favoriteId = item.id
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun EmptyListAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            LottieAnimation(
                composition,
                modifier = Modifier,
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
            RickAndMortyText(
                text = stringResource(R.string.favorite_screen_empty_list_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun DetailContentItemViewPreview() {
    Content(Modifier, false, 1, false, false, listOf(), triggerEvent = {}, clickDetail = {})
}