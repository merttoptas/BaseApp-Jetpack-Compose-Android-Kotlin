package com.merttoptas.composebase.features.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortySnackBar(
    snackbarHostState: SnackbarHostState,
) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            containerColor = MaterialTheme.colorScheme.onSurface,
            snackbarData = data,
            shape = MaterialTheme.shapes.medium
        )
    }
}

@Preview
@Composable
private fun BodyPreview() {
    RickAndMortySnackBar(
        snackbarHostState = SnackbarHostState(),
    )
}