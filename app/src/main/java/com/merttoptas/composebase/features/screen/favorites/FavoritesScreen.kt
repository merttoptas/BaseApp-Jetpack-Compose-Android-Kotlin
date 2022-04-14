package com.merttoptas.composebase.features.screen.favorites

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.merttoptas.composebase.domain.viewstate.favorites.FavoritesViewState
import com.merttoptas.composebase.features.component.*

/**
 * Created by merttoptas on 30.03.2022
 */

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    navigateCharacterDetail: (FavoriteEntity) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(R.string.favorite_screen_title),
                elevation = 10.dp,
                navigationIcon = {
                    IconButton(onClick = { }) {
                    }
                },
                actions = {
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
                },
            )
        },
        content = {
            Content(
                viewState = viewModel.uiState.collectAsState().value,
                triggerEvent = {
                    viewModel.onTriggerEvent(it)
                },
                clickDetail = {
                    navigateCharacterDetail.invoke(it)
                })
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun Content(
    viewState: FavoritesViewState,
    triggerEvent: (FavoritesViewEvent) -> Unit,
    clickDetail: (FavoriteEntity) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            RickAndMortyAlertDialog(
                isDisplayed = viewState.isDisplay,
                onClickDelete = {
                    if (viewState.isAllDeleteFavorites) {
                        triggerEvent.invoke(FavoritesViewEvent.OnDeleteAllFavorites)
                    } else {
                        triggerEvent.invoke(FavoritesViewEvent.OnDeleteFavorite)
                    }
                    triggerEvent.invoke(
                        FavoritesViewEvent.OnDisplayChange(
                            viewState.copy(
                                isDisplay = false,
                                favoriteId = viewState.favoriteId
                            )
                        )
                    )
                },
                onBackPressed = {
                    triggerEvent.invoke(
                        FavoritesViewEvent.OnDisplayChange(
                            viewState.copy(
                                isDisplay = false,
                                favoriteId = viewState.favoriteId
                            )
                        )
                    )
                },
            )
        }

        if (viewState.isLoading.not() && viewState.favoritesList.isEmpty()) {
            EmptyListAnimation()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (viewState.isLoading) {
                items(10) {
                    RickAndMortyCharacterShimmer()
                }
            } else if (viewState.favoritesList.isNotEmpty()) {
                items(items = viewState.favoritesList) { item ->
                    RickAndMortyFavoriteRowCard(
                        status = item.status,
                        detailClick = {
                            clickDetail.invoke(item)
                        },
                        dto = item,
                        onDeleteClick = {
                            triggerEvent.invoke(
                                FavoritesViewEvent.OnDisplayChange(
                                    viewState.copy(
                                        isDisplay = true,
                                        favoriteId = item.id
                                    )
                                )
                            )
                        }
                    )
                }
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
    Content(viewState = hiltViewModel(), triggerEvent = {}, clickDetail = {})
}