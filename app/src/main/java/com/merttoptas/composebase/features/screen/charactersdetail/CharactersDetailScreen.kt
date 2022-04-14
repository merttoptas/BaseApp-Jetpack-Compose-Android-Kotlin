package com.merttoptas.composebase.features.screen.charactersdetail

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.domain.viewstate.charactersdetail.CharactersDetailViewState
import com.merttoptas.composebase.features.component.RickAndMortyNetworkImage
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyText
import com.merttoptas.composebase.features.component.RickAndMortyTopBar
import com.merttoptas.composebase.features.screen.characters.CharactersScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 13.03.2022
 */

@Composable
fun CharactersDetailScreen(
    viewModel: CharactersDetailViewModel = viewModel(),
    navigateToBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is CharactersDetailViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message ?: "")
                    }
                }
            }
        }
    }

    RickAndMortyScaffold(
        topBar = {
            RickAndMortyTopBar(
                elevation = 10.dp,
                navigationIcon = {
                    IconButton(onClick = {
                        navigateToBack()
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {}
                },
                text = stringResource(id = R.string.character_detail_screen_title)
            )
        },
        content = {
            Content(
                viewState
            )
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Content(viewState: CharactersDetailViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(viewState)
        CharacterInfoContainer(viewState)
    }
}

@Composable
private fun CharacterImage(viewState: CharactersDetailViewState) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(top = 20.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            2.dp,
            color = if (viewState.data?.status == Status.Alive) Color.Green else Color.Red
        ),
    ) {
        RickAndMortyNetworkImage(
            imageURL = viewState.data?.image,
            modifier = Modifier
                .fillMaxSize(),
            placeholder = R.drawable.ic_place_holder,
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
private fun CharacterInfoContainer(viewState: CharactersDetailViewState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            CharacterInfoRow(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.character_detail_card_name),
                value = viewState.data?.name ?: ""
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_species),
                value = viewState.data?.species ?: ""
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_gender),
                value = viewState.data?.gender ?: ""
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_last_know_location),
                value = viewState.data?.origin?.name ?: ""
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_location),
                value = viewState.data?.location?.name ?: ""
            )
            Divider(thickness = 0.5.dp)
        }
    }
}

@Composable
private fun CharacterInfoRow(modifier: Modifier, text: String, value: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        RickAndMortyText(
            text = text,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface
        )

        RickAndMortyText(
            text = value,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary
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
    CharactersDetailScreen(viewModel = hiltViewModel(), navigateToBack = {})
}