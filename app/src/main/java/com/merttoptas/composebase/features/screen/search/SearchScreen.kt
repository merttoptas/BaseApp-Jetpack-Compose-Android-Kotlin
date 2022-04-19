@file:OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)

package com.merttoptas.composebase.features.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.viewstate.search.SearchViewState
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 9.04.2022
 */

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDetail: (CharacterDto?) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

    val viewState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.search_screen_title),
                elevation = 10.dp,
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            state.show()
                        }
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter),
                            contentDescription = null
                        )
                    }
                },
            )
        },
        content = {
            ModalBottomSheetLayout(
                sheetState = state,
                sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                sheetContent = {
                    BottomSheetLayout(viewModel, viewState, state)
                }
            ) {
                Content(
                    isLoading = viewState.isLoading,
                    searchText = viewState.searchText,
                    pagedData = viewState.pagedData,
                    onTriggerEvent = {
                        viewModel.onTriggerEvent(it)
                    },
                    clickDetail = {
                        navigateToDetail.invoke(it)
                    },
                    onTextChange = {
                        viewModel.searchText(it)
                    }
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Content(
    isLoading: Boolean,
    searchText: String?,
    pagedData: Flow<PagingData<CharacterDto>>?,
    onTriggerEvent: (SearchViewEvent) -> Unit,
    clickDetail: (CharacterDto?) -> Unit,
    onTextChange: (String) -> Unit
) {

    var pagingItems: LazyPagingItems<CharacterDto>? = null
    pagedData?.let {
        pagingItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        RickAndMortySearchBar(
            modifier = Modifier.padding(top = 15.dp),
            text = searchText.orEmpty(),
            onTextChange = {
                onTextChange.invoke(it)
            },
            onClickSearch = {
                onTriggerEvent.invoke(SearchViewEvent.NewSearchEvent)
                keyboardController?.hide()
            }
        )
        ShowSearchList(isLoading, pagingItems, pagedData, clickDetail, onTriggerEvent)
    }
}

@Composable
private fun ShowSearchList(
    isLoading: Boolean,
    pagingItems: LazyPagingItems<CharacterDto>?,
    pagedData: Flow<PagingData<CharacterDto>>?,
    clickDetail: (CharacterDto?) -> Unit,
    onTriggerEvent: (SearchViewEvent) -> Unit
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
            items(items = pagingItems) { item ->
                RickAndMortyCharactersCard(
                    status = item?.status ?: Status.Unknown,
                    detailClick = {
                        clickDetail.invoke(item)
                    },
                    dto = item,
                    onTriggerEvent = {
                        onTriggerEvent.invoke(SearchViewEvent.UpdateFavorite(it))
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomSheetLayout(
    viewModel: SearchViewModel,
    viewState: SearchViewState,
    state: ModalBottomSheetState
) {
    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        val (title, divider, description, button) = createRefs()
        RickAndMortyText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent

            },
            text = stringResource(id = R.string.search_modal_title)
        )
        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    width = Dimension.fillToConstraints

                }
                .background(color = MaterialTheme.colors.primaryVariant)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
                .constrainAs(description) {
                    top.linkTo(divider.bottom)
                },
        ) {
            RickAndMortyText(
                text = stringResource(id = R.string.search_modal_status_title),
                color = MaterialTheme.colors.onSurface
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                viewState.status.forEach {
                    RickAndMortySelectableText(
                        modifier = Modifier.weight(1f),
                        isSelected = it.selected,
                        text = it.name
                    ) {
                        viewModel.selectStatus(it.name)
                    }
                    Spacer(modifier = Modifier.width(20.dp))

                }
                Spacer(modifier = Modifier.width(20.dp))
            }

            RickAndMortyText(
                text = stringResource(id = R.string.search_modal_gender_title),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colors.onSurface
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                viewState.gender.forEach {
                    RickAndMortySelectableText(
                        modifier = Modifier.weight(0.5f),
                        isSelected = it.selected,
                        text = it.name,
                        style = MaterialTheme.typography.body2
                    ) {
                        viewModel.selectGender(it.name)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
        RickAndMortyButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom, 30.dp)
            },
            onClick = {
                viewModel.onTriggerEvent(SearchViewEvent.NewSearchEvent)
                scope.launch {
                    state.hide()
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            borderColor = MaterialTheme.colors.primary,
            text = stringResource(id = R.string.search_modal_button_text)
        )
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
    SearchScreen(
        viewModel = hiltViewModel(),
        navigateToDetail = {}
    )
}