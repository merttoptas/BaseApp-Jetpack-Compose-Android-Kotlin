package com.merttoptas.composebase.features.navigation

import com.merttoptas.composebase.R
import com.merttoptas.composebase.utils.Constants

/**
 * Created by merttoptas on 9.03.2022
 */

enum class BottomNav(
    val route: String,
    val IconId: Int,
    val screenName: String
) {
    CHARACTERS(
        NavScreen.Characters.route,
        R.drawable.ic_outline_people,
        Constants.SCREEN_NAME_CHARACTERS
    ),
    EPISODES(
        NavScreen.Episodes.route,
        R.drawable.ic_baseline_movie_creation_24,
        Constants.SCREEN_NAME_EPISODES
    ),
    FAVORITES(
        NavScreen.Favorites.route,
        R.drawable.ic_baseline_favorite_24,
        Constants.SCREEN_NAME_FAVORITES
    ),
    LOCATIONS(
        NavScreen.Locations.route,
        R.drawable.ic_baseline_location_on,
        Constants.SCREEN_NAME_LOCATIONS
    ),
    SETTINGS(
        NavScreen.Settings .route,
        R.drawable.ic_baseline_settings,
        Constants.SCREEN_NAME_SETTINGS
    )
}