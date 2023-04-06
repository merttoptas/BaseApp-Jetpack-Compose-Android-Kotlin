package com.merttoptas.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by merttoptas on 9.04.2022
 */
@Composable
fun RickAndMortySelectableText(
    modifier: Modifier,
    text: String = "",
    isSelected: Boolean?,
    style : TextStyle = MaterialTheme.typography.bodySmall,
    onClick: () -> Unit = {},
) {
    val bgColor =
        if (isSelected == true) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background
    val textColor = if (isSelected == true) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary

    RickAndMortyText(
        text = text,
        color = textColor,
        style = style,
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = bgColor,
                shape = RoundedCornerShape(14.dp)
            )
            .height(50.dp)
            .wrapContentSize()

    )
}

@Preview
@Composable
private fun BodyPreview() {
    RickAndMortySelectableText(
        modifier = Modifier.fillMaxWidth(),
        text = "Text",
        isSelected = false,
        onClick = {}
    )
}