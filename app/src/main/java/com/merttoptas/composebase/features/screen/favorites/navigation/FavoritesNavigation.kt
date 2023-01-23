@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.composebase.features.screen.favorites.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.merttoptas.composebase.data.model.FavoriteEntity
import com.merttoptas.composebase.features.screen.favorites.FavoritesScreen

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val favoritesNavigationRoute = "favorites_route"

fun NavController.navigateToFavorites(
    navOptions: NavOptions? = null
) {
    this.navigate(favoritesNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen(navigateCharacterDetail: (FavoriteEntity) -> Unit) {
    composable(
      favoritesNavigationRoute,
        content = {
            FavoritesScreen(
                viewModel = hiltViewModel(),
                navigateCharacterDetail = {
                    navigateCharacterDetail.invoke(it)
                }
            )
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
    )
}