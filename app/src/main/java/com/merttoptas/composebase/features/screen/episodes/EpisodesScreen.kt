package com.merttoptas.composebase.features.screen.episodes

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.features.component.*
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.EpisodesResultResponse
import com.merttoptas.composebase.features.ui.theme.ComposeBaseTheme
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 19.03.2022
 */

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewState = viewModel.uiState.collectAsState().value

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is EpisodesViewEvent.SnackBarError -> {
                        snackbarHostState.showSnackbar(it.message.orEmpty())
                    }
                }
            }
        }
    }

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHostState = snackbarHostState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.episodes_screen_title),
            )
        },
        content = {
            Content(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(it),
                isLoading = viewState.isLoading,
                data = viewState.data,
            )
        },
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    isLoading: Boolean,
    data: List<EpisodesResultResponse>?,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isLoading) {
                items(10) {
                    RickAndMortyEpisodesShimmer()
                }
            } else {
                items(items = data ?: listOf()) { item ->
                    RickAndMortyEpisodesCard(
                        name = item.name.orEmpty(),
                        date = item.airDate.orEmpty(),
                        episode = item.episode.orEmpty(),
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
    ComposeBaseTheme {
        Content(modifier = Modifier, isLoading = false, data = listOf(
            EpisodesResultResponse(
                id = 1,
                name = "Pilot",
                airDate = "December 2, 2013",
                episode = "S01E01",
                characters = listOf(),
                url = "https://rickandmortyapi.com/api/episode/1",
                created = "2017-11-10T12:56:33.798Z"
            )
        ))
    }
}