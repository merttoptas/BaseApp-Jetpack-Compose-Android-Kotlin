package com.merttoptas.composebase.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.merttoptas.composebase.R
import com.merttoptas.composebase.features.ui.theme.ComposeBaseTheme

/**
 * Created by merttoptas on 9.04.2022
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortySearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (text: String) -> Unit,
    onSearchChange: (String) -> Unit,
    onActiveSearch: (Boolean) -> Unit,
    active: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    SearchBar(
        modifier = modifier.fillMaxWidth(),
        query = text,
        onQueryChange = onTextChange,
        onSearch = onSearchChange,
        active = active,
        onActiveChange = onActiveSearch,
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        },
        placeholder = { RickAndMortyText(text = "Search character for name",) },
        colors = SearchBarDefaults.colors(),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    RickAndMortySearchBar(
        text = "",
        onTextChange = {},
        onSearchChange = {},
        onActiveSearch = {},
        active = false,
        content = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBarDark() {
    ComposeBaseTheme(darkTheme = true) {
        RickAndMortySearchBar(
            text = "",
            onTextChange = {},
            onSearchChange = {},
            onActiveSearch = {},
            active = false,
            content = {}
        )
    }
}