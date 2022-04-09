package com.merttoptas.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.R

/**
 * Created by merttoptas on 9.04.2022
 */
@Composable
fun RickAndMortySelectableText(
    modifier: Modifier,
    text: String = "",
    isSelected: Boolean?,
    style : TextStyle = MaterialTheme.typography.body1,
    onClick: () -> Unit = {},
) {
    val bgColor =
        if (isSelected == true) colorResource(id = R.color.light_blue) else colorResource(
            id = R.color.pale_grey
        )
    val textColor = if (isSelected == true) Color.White else MaterialTheme.colors.primary

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