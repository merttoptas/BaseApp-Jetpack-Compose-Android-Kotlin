package com.merttoptas.composebase.features.screen.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.screen.characters.CharactersScreen
import kotlinx.serialization.Serializable

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val charactersNavigationRoute = "characters_route"

@Serializable
data object Characters : Route()

fun NavController.navigateCharacter(
    navOptions: NavOptions? = null
) {
    this.navigate(charactersNavigationRoute, navOptions)
}

fun NavGraphBuilder.charactersScreen(navigateToDetail: (CharacterDto?) -> Unit) {
    composable<Characters> {
        CharactersScreen(navigateToDetail = navigateToDetail)
    }
}