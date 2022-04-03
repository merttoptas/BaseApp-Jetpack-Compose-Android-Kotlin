package com.merttoptas.composebase.features.screen.characters

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.component.RickAndMortyCharacterShimmer
import com.merttoptas.composebase.features.component.RickAndMortyCharactersCard
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyTopBar
import com.merttoptas.composebase.features.navigation.NavScreen
import com.merttoptas.composebase.utils.Utility.rememberFlowWithLifecycle
import com.merttoptas.composebase.utils.Utility.toJson

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
                text = stringResource(id = R.string.characters_screen_title),
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
            } else if (viewState.pagedData != null && pagingItems != null) {
                itemsIndexed(items = pagingItems!!) { index, item ->
                    RickAndMortyCharactersCard(
                        status = item?.status ?: Status.Unknown,
                        detailClick = {
                            navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${item.toJson()}"))
                        },
                        viewModel = viewModel,
                        dto = item
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
    CharactersScreen(viewModel = hiltViewModel(), navController = rememberNavController())
}