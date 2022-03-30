package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

/**
 * Created by merttoptas on 30.03.2022
 */

@Composable
fun RickAndMortyAlertDialog(
    isDisplayed: Boolean,
    onClickDelete: () -> Unit,
    onBackPressed: () -> Unit,
) {
    if (isDisplayed) {
        Dialog(
            onDismissRequest = { }
        ) {
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    RickAndMortyText(
                        text = "Favorite Delete",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    RickAndMortyText(
                        text = "Do you approve to delete this favorite?",
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        RickAndMortyButton(
                            onClick = { onClickDelete() },
                            borderColor = MaterialTheme.colors.error,
                            text = "Delete",
                            modifier = Modifier
                                .height(40.dp)
                                .weight(0.5f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        RickAndMortyButton(
                            onClick = { onBackPressed() },
                            borderColor = MaterialTheme.colors.secondary,
                            text = "Cancel",
                            modifier = Modifier
                                .height(40.dp)
                                .weight(0.5f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        )
                    }
                }
            }
        }
    }
}