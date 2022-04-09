package com.merttoptas.composebase.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.R

/**
 * Created by merttoptas on 9.04.2022
 */

@Composable
fun RickAndMortySearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (text: String) -> Unit,
    onClickSearch: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(elevation = 10.dp)
    ) {
        RickAndMortyTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                RickAndMortyText(
                    text = "Search character for name",
                    color = colorResource(R.color.light_gray),
                )
            },
        )
        Box(
            Modifier
                .background(
                    color = MaterialTheme.colors.onPrimary,
                    shape = RoundedCornerShape(14.dp)
                )
                .size(56.dp)
                .clickable { onClickSearch() }
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PreviewMinimaSearchBar() {
    RickAndMortySearchBar(text = "", onTextChange = {}, onClickSearch = {})
}