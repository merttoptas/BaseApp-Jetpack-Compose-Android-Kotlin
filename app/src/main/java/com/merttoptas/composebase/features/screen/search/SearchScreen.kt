package com.merttoptas.composebase.features.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.merttoptas.composebase.R
import com.merttoptas.composebase.domain.viewstate.search.SearchViewState
import com.merttoptas.composebase.features.component.*
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 9.04.2022
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val viewState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = "Search",
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
                Content(viewModel, viewState)
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(viewModel: SearchViewModel, viewState: SearchViewState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        RickAndMortySearchBar(
            modifier = Modifier.padding(top = 15.dp),
            text = viewState.searchText ?: "",
            onTextChange = { viewModel.searchText(it) },
            onClickSearch = {
                // viewModel.onTriggerEvent(SearchEvent.NewSearchEvent)
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomSheetLayout(
    viewModel: SearchViewModel,
    viewState: SearchViewState,
    state: ModalBottomSheetState
) {
    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
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
            text = "Filter"
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
            RickAndMortyText(text = "Status", color = MaterialTheme.colors.onSurface)
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
                text = "Gender",
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
            borderColor = MaterialTheme.colors.primaryVariant,
            text = "Apply"
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
    com.merttoptas.composebase.features.screen.settings.SettingsScreen()
}