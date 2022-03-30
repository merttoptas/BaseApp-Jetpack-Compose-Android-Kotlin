package com.merttoptas.composebase.features.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by merttoptas on 30.03.2022
 */

@Composable
fun RickAndMortyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    borderColor: Color,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled,
        colors = colors,
        shape = RoundedCornerShape(20),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        content = {
            RickAndMortyText(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )
        }
    )
}


@Preview
@Composable
private fun BodyPreview() {
    RickAndMortyButton(
        enabled = true,
        modifier = Modifier,
        onClick = {},
        borderColor = MaterialTheme.colors.primaryVariant,
        text = "Minima Button"
    )
}