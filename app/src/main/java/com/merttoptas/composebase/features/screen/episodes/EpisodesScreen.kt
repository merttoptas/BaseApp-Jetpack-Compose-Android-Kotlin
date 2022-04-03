package com.merttoptas.composebase.features.screen.episodes

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.R
import com.merttoptas.composebase.features.screen.characters.CharactersScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 19.03.2022
 */

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is EpisodesViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message ?: "")
                    }
                }
            }
        }
    }


    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.episodes_screen_title),
                actions = {
                    IconButton(onClick = {

                    }) {

                    }
                },
                elevation = 10.dp,
            )
        },
        content = { Content(viewModel) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(viewModel: EpisodesViewModel) {
    val viewState by viewModel.uiState.collectAsState()

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
                    RickAndMortyEpisodesShimmer()
                }
            } else {
                items(items = viewState.data ?: listOf()) { item ->
                    RickAndMortyEpisodesCard(
                        name = item.name ?: "",
                        date = item.airDate ?: "",
                        episode = item.episode ?: "",
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
    EpisodesScreen(viewModel = hiltViewModel())
}