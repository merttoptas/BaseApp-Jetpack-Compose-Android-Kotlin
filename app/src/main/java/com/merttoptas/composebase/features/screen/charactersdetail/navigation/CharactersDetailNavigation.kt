@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.composebase.features.screen.charactersdetail.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.merttoptas.composebase.features.screen.charactersdetail.CharactersDetailScreen

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val charactersDetailNavigationRoute = "characters_detail_route"

fun NavController.navigateCharactersDetail(
    characterDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(charactersDetailNavigationRoute.plus("?characterDetail=${characterDetail}"), navOptions)
}

fun NavGraphBuilder.charactersDetailScreen(navigateToBack: () -> Unit) {
    composable(
        charactersDetailNavigationRoute.plus("?characterDetail={characterDetail}"),
        content = {
            CharactersDetailScreen(
                viewModel = hiltViewModel(),
                navigateToBack = navigateToBack
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