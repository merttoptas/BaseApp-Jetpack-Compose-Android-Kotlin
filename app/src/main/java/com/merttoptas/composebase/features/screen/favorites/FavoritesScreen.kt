package com.merttoptas.composebase.features.screen.favorites

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.merttoptas.composebase.BuildConfig
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.viewstate.favorites.FavoritesViewState
import com.merttoptas.composebase.domain.viewstate.settings.SettingsViewState
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.features.navigation.NavScreen
import com.merttoptas.composebase.features.screen.settings.SettingsViewModel
import com.merttoptas.composebase.utils.Utility
import com.merttoptas.composebase.utils.Utility.toJson

/**
 * Created by merttoptas on 30.03.2022
 */

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = "Favorites",
                elevation = 10.dp,
            )
        },
        content = { Content(viewModel, navController) },
        backgroundColor = MaterialTheme.colors.background
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(viewModel: FavoritesViewModel, navController: NavController) {
    val viewState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            RickAndMortyAlertDialog(
                isDisplayed = viewState.isDisplay,
                onClickDelete = {
                    viewModel.onDeleteFavorite()
                    viewModel.onDisplayChange(false, viewState.favoriteId)
                },
                onBackPressed = { viewModel.onDisplayChange(false, viewState.favoriteId) },
            )
        }


        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (viewState.isLoading) {
                items(10) {
                    RickAndMortyCharacterShimmer()
                }
            } else {
                items(items = viewState.favoritesList) { item ->
                    RickAndMortyFavoriteRowCard(
                        status = item.status,
                        detailClick = {
                            navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${item.toJson()}"))
                        },
                        dto = item,
                        onDeleteClick = { viewModel.onDisplayChange(true, item.id) }
                    )
                }
            }
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
    Content(viewModel = hiltViewModel(), navController = rememberNavController())
}