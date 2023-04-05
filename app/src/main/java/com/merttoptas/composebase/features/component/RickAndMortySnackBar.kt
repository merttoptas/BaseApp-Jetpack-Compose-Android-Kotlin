package com.merttoptas.composebase.features.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortySnackBar(
    snackbarHostState: SnackbarHostState,
    snackBarEnum: SnackBarEnum
) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            contentColor = Color(integerResource(id = snackBarEnum.backgroundColor)),
            snackbarData = data,
            shape = MaterialTheme.shapes.medium
        )
    }
}

sealed class SnackBarEnum(val backgroundColor: Int) {
    //TODO("Add your own SnackBarEnum")
    object SUCCESS : SnackBarEnum(com.merttoptas.composebase.R.color.black)
    object ERROR : SnackBarEnum(com.merttoptas.composebase.R.color.black)
}

@Preview
@Composable
private fun BodyPreview() {
    RickAndMortySnackBar(
        snackbarHostState = SnackbarHostState(),
        SnackBarEnum.SUCCESS
    )
}