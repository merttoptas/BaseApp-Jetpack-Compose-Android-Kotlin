package com.merttoptas.composebase.features.screen.search

import android.content.res.Configuration
import android.view.SearchEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.merttoptas.composebase.domain.viewstate.search.SearchViewState
import com.merttoptas.composebase.features.component.*

/**
 * Created by merttoptas on 9.04.2022
 */

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = "Search",
                elevation = 10.dp,
            )
        },
        content = { Content(viewModel, viewState) },
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
            text = "Search",
            onTextChange = {
                //viewModel.searchText(it
             },
            onClickSearch = {
               // viewModel.onTriggerEvent(SearchEvent.NewSearchEvent)
            }
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