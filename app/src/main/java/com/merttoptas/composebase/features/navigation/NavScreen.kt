package com.merttoptas.composebase.features.navigation

/**
 * Created by merttoptas on 9.03.2022
 */

sealed class NavScreen(val route: String) {
    object Splash : NavScreen("splash")
    object Settings : NavScreen("settings")
    object Characters : NavScreen("characters")
    object Episodes : NavScreen("episodes")
    object CharacterDetail : NavScreen("character_detail")
    object Favorites : NavScreen("favorites")
    object Locations : NavScreen("locations")
}