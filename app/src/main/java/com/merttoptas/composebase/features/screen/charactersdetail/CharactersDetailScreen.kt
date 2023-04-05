package com.merttoptas.composebase.features.screen.charactersdetail

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
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
import com.merttoptas.composebase.R
import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.features.component.RickAndMortyNavigateBack
import com.merttoptas.composebase.features.component.RickAndMortyNetworkImage
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyText
import com.merttoptas.composebase.features.component.RickAndMortyTopBar
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 13.03.2022
 */

@Composable
fun CharactersDetailScreen(
    viewModel: CharactersDetailViewModel = viewModel(),
    navigateToBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is CharactersDetailViewEvent.SnackBarMessage -> {
                        snackbarHostState.showSnackbar(it.message.orEmpty())
                    }
                }
            }
        }
    }

    RickAndMortyScaffold(
        topBar = {
            RickAndMortyTopBar(
                navigationIcon = {
                    RickAndMortyNavigateBack(navigateToBack)
                },
                text = stringResource(id = R.string.character_detail_screen_title)
            )
        },
        content = {
            Content(
                modifier = Modifier.padding(it),
                viewState.data
            )
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
private fun Content(modifier: Modifier, data: Result?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(data)
        CharacterInfoContainer(data)
    }
}

@Composable
private fun CharacterImage(data: Result?) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(top = 20.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            2.dp,
            color = if (data?.status == Status.Alive) Color.Green else Color.Red
        ),
    ) {
        RickAndMortyNetworkImage(
            imageURL = data?.image,
            modifier = Modifier
                .fillMaxSize(),
            placeholder = R.drawable.ic_place_holder,
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
private fun CharacterInfoContainer(data: Result?) {
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
                value = data?.name.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_species),
                value = data?.species.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_gender),
                value = data?.gender.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_last_know_location),
                value = data?.origin?.name.orEmpty()
            )
            Divider(thickness = 0.5.dp)
            CharacterInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.character_detail_card_location),
                value = data?.location?.name.orEmpty()
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
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        RickAndMortyText(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
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