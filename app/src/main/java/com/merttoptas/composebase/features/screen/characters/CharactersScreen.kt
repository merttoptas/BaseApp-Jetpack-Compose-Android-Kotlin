package com.merttoptas.composebase.features.screen.characters

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.component.RickAndMortyCharacterShimmer
import com.merttoptas.composebase.features.component.RickAndMortyCharactersCard
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyTopBar
import com.merttoptas.composebase.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 13.03.2022
 */

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetail: (CharacterDto?) -> Unit
) {
    val viewState = viewModel.uiState.collectAsState().value

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.characters_screen_title),
            )
        },
        content = {
            Content(
                isLoading = viewState.isLoading,
                pagedData = viewState.pagedData,
                onTriggerEvent = {
                    viewModel.onTriggerEvent(it)
                },
                clickDetail = {
                    navigateToDetail.invoke(it)
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}

@Composable
private fun Content(
    isLoading: Boolean = false,
    pagedData: Flow<PagingData<CharacterDto>>? = null,
    onTriggerEvent: (CharactersViewEvent) -> Unit,
    clickDetail: (CharacterDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<CharacterDto>? = null
    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isLoading) {
                items(10) {
                    RickAndMortyCharacterShimmer()
                }
            } else if (pagedData != null && pagingItems != null) {
                pagingItems?.let { safePagingItems ->

                    items(
                        count = safePagingItems.itemCount,
                        key = safePagingItems.itemKey { item -> item.id ?: 0 },
                        contentType = safePagingItems.itemContentType { "ProductGridListItem" },
                    ) {
                        val item = safePagingItems[it]
                        RickAndMortyCharactersCard(
                            status = item?.status ?: Status.Unknown,
                            detailClick = {
                                clickDetail.invoke(item)
                            },
                            dto = item,
                            onTriggerEvent = {
                                onTriggerEvent.invoke(CharactersViewEvent.UpdateFavorite(it))
                            }
                        )

                    }
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
    CharactersScreen(viewModel = hiltViewModel(), navigateToDetail = {})
}