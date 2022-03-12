package com.merttoptas.composebase.features.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * Created by merttoptas on 9.03.2022
 */

sealed class NavScreen(val route: String) {
    object Splash : NavScreen("splash")
    object Settings : NavScreen("settings")
    object Characters : NavScreen("characters")
    object Episodes : NavScreen("episodes")
    object CharacterDetail : NavScreen("characterDetail")
    object Favorites : NavScreen("favorites")
    object Locations : NavScreen("locations")
}