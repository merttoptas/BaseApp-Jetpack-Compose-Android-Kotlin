package com.merttoptas.composebase.features.screen.charactersdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.features.screen.charactersdetail.CharactersDetailScreen
import kotlinx.serialization.Serializable

/**
 * Created by mertcantoptas on 23.01.2023
 */

@Serializable
data class CharactersDetail(val charactersDetail: Result) : Route()

fun NavController.navigateCharactersDetail(
    characterDetail: Result,
    navOptions: NavOptions? = null
) {
    this.navigate(CharactersDetail(characterDetail), navOptions)
}

fun NavGraphBuilder.charactersDetailScreen(navigateToBack: () -> Unit) {
    composable<CharactersDetail>(
        content = {
            CharactersDetailScreen(
                navigateToBack = navigateToBack
            )
        }
    )
}