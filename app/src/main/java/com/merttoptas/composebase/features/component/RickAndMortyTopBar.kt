package com.merttoptas.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by merttoptas on 12.03.2022
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyTopBar(
    modifier: Modifier = Modifier,
    text: String,
    title: @Composable () -> Unit = {
        RickAndMortyText(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentWidth()
                .background(MaterialTheme.colorScheme.background),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1
        )
    },
    navigationIcon: @Composable (() -> Unit) = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    RickAndMortyTopBar(
        modifier = Modifier,
        text = "Value",
        title = {
            RickAndMortyText(
                text = "Title",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),

                )
        },
    )
}
