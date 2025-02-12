package com.merttoptas.composebase.features.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.viewstate.search.SearchViewState
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.features.screen.characters.CharactersViewEvent
import com.merttoptas.composebase.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.SoftwareKeyboardController

/**
 * Created by merttoptas on 9.04.2022
 */

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class
)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToDetail: (CharacterDto?) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val skipPartiallyExpanded by remember { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val viewState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHostState = snackbarHostState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.search_screen_title),
                actions = {
                    IconButton(onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                viewModel.onOpenBottomSheet(true)
                            } else {
                                viewModel.onOpenBottomSheet(false)
                            }
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
            Content(
                modifier = Modifier.padding(it),
                viewState = viewState,
                onTriggerEvent = viewModel::onTriggerEvent,
                clickDetail = navigateToDetail::invoke,
                onTextChange = viewModel::searchText,
                onActiveChange = viewModel::onActiveChange,
                onDismissRequest = {
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        if (bottomSheetState.isVisible) {
                            viewModel.onOpenBottomSheet(false)
                        }
                    }
                },
                selectStatus = viewModel::selectStatus,
                selectGender = viewModel::selectGender,
                newSearchClicked = {
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) {
                            viewModel.onOpenBottomSheet(false)
                        }
                    }
                    viewModel.onSearch()
                },
                bottomSheetState = bottomSheetState,
            )

        },
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier,
    viewState: SearchViewState,
    onTriggerEvent: (SearchViewEvent) -> Unit,
    clickDetail: (CharacterDto?) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onTextChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    selectStatus: (String) -> Unit,
    selectGender: (String) -> Unit,
    newSearchClicked: () -> Unit,
    bottomSheetState: SheetState,
) {
    var pagingItems: LazyPagingItems<CharacterDto>? = null
    viewState.pagedData?.let {
        pagingItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    if (viewState.openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            modifier = Modifier,
            sheetState = bottomSheetState,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            BottomSheetLayout(
                selectStatus = selectStatus,
                selectGender = selectGender,
                newSearchClicked = newSearchClicked,
                viewState = viewState
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        RickAndMortySearchBar(
            modifier = Modifier.fillMaxWidth().pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onActiveChange.invoke(false)
                        keyboardController?.hide()
                    }
                )
            },
            text = viewState.searchText.orEmpty(),
            onTextChange = {
                onTextChange.invoke(it)
                newSearchClicked.invoke()
            },
            onSearchChange = { onActiveChange.invoke(false) },
            active = viewState.active,
            onActiveSearch = onActiveChange::invoke,
            content = {
                SuggestionList(
                    suggestions = viewState.suggestion,
                    onTextChange = onTextChange,
                    onNewSearch = newSearchClicked,
                    onActiveChange = onActiveChange,
                    keyboardController = keyboardController
                )
            }
        )
        ShowSearchList(
            viewState.isLoading,
            pagingItems,
            viewState.pagedData,
            clickDetail,
            onTriggerEvent
        )
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
                            onTriggerEvent.invoke(SearchViewEvent.UpdateFavorite(it))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomSheetLayout(
    selectStatus: (String) -> Unit,
    selectGender: (String) -> Unit,
    newSearchClicked: () -> Unit,
    viewState: SearchViewState,
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        val (title,  description, button) = createRefs()
        RickAndMortyText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent

            },
            text = stringResource(id = R.string.search_modal_title)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                },
        ) {
            RickAndMortyText(
                text = stringResource(id = R.string.search_modal_status_title),
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                viewState.status.forEach {
                    RickAndMortySelectableText(
                        modifier = Modifier.weight(1f),
                        isSelected = it.selected,
                        text = it.name
                    ) {
                        selectStatus.invoke(it.name)
                    }
                    Spacer(modifier = Modifier.width(20.dp))

                }
                Spacer(modifier = Modifier.width(20.dp))
            }

            RickAndMortyText(
                text = stringResource(id = R.string.search_modal_gender_title),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                viewState.gender.forEach {
                    RickAndMortySelectableText(
                        modifier = Modifier.weight(0.5f),
                        isSelected = it.selected,
                        text = it.name,
                        style = MaterialTheme.typography.bodyMedium
                    ) {
                        selectGender.invoke(it.name)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
        RickAndMortyButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom, 30.dp)
            },
            onClick = newSearchClicked,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            borderColor = MaterialTheme.colorScheme.primary,
            text = stringResource(id = R.string.search_modal_button_text)
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SuggestionList(
    suggestions: List<String>,
    onTextChange: (String) -> Unit,
    onNewSearch: () -> Unit,
    onActiveChange: (Boolean) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(suggestions) { suggest ->
            ListItem(
                headlineContent = { RickAndMortyText(suggest) },
                supportingContent = { RickAndMortyText("Character") },
                leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                modifier = Modifier.clickable {
                    onActiveChange.invoke(false)
                    onTextChange.invoke(suggest)
                    onNewSearch.invoke()
                    keyboardController?.hide()
                }
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
    SearchScreen(
        viewModel = hiltViewModel(),
        navigateToDetail = {}
    )
}