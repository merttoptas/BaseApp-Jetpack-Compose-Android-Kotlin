package com.merttoptas.composebase.features.screen.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.data.model.FavoriteEntity
import com.merttoptas.composebase.features.screen.favorites.FavoritesScreen
import kotlinx.serialization.Serializable

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val favoritesNavigationRoute = "favorites_route"

@Serializable
data object Favorites : Route()

fun NavController.navigateToFavorites(
    navOptions: NavOptions? = null
) {
    this.navigate(Favorites, navOptions)
}

fun NavGraphBuilder.favoritesScreen(navigateCharacterDetail: (FavoriteEntity) -> Unit) {
    composable<Favorites> {
        FavoritesScreen(
            navigateCharacterDetail = navigateCharacterDetail
        )
    }
}