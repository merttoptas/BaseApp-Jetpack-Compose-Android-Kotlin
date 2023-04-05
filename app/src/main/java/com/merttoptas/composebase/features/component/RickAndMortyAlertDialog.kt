package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.merttoptas.composebase.features.ui.theme.ComposeBaseTheme
import com.merttoptas.composebase.R

/**
 * Created by merttoptas on 30.03.2022
 */

@ExperimentalMaterial3Api
@Composable
fun RickAndMortyAlertDialog(
    isDisplayed: Boolean,
    onClickDelete: () -> Unit,
    onBackPressed: () -> Unit,
) {
    if (isDisplayed) {
        AlertDialog(
            onDismissRequest = onBackPressed,
            title = {
                Text(
                    text = stringResource(id = R.string.dialog_title),
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_message),
                )
            },
            dismissButton = {
                TextButton(
                    onClick = onBackPressed,
                ) {
                    Text(text = stringResource(id = R.string.dialog_button_negative))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onClickDelete,
                ) {
                    Text(text = stringResource(id = R.string.dialog_button_positive))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewAlertDialog() {
    ComposeBaseTheme {
        RickAndMortyAlertDialog(
            isDisplayed = true,
            onClickDelete = {},
            onBackPressed = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewAlertDialogDark() {
    ComposeBaseTheme(darkTheme = true) {
        RickAndMortyAlertDialog(
            isDisplayed = true,
            onClickDelete = {},
            onBackPressed = {},
        )
    }
}