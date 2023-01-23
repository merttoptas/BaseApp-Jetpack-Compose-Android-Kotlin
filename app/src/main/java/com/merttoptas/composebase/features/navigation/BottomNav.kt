package com.merttoptas.composebase.features.navigation

import com.merttoptas.composebase.R
import com.merttoptas.composebase.features.screen.characters.navigation.charactersNavigationRoute
import com.merttoptas.composebase.features.screen.episodes.navigation.episodesNavigationRoute
import com.merttoptas.composebase.features.screen.favorites.navigation.favoritesNavigationRoute
import com.merttoptas.composebase.features.screen.search.navigation.searchNavigationRoute
import com.merttoptas.composebase.features.screen.settings.navigation.settingsNavigationRoute
import com.merttoptas.composebase.utils.Constants

/**
 * Created by merttoptas on 9.03.2022
 */

enum class BottomNav(
    val route: String,
    val iconId: Int,
    val screenName: String
) {
    CHARACTERS(
        charactersNavigationRoute,
        R.drawable.ic_outline_people,
        Constants.SCREEN_NAME_CHARACTERS
    ),
    EPISODES(
       episodesNavigationRoute,
        R.drawable.ic_baseline_movie_creation_24,
        Constants.SCREEN_NAME_EPISODES
    ),
    FAVORITES(
        favoritesNavigationRoute,
        R.drawable.ic_baseline_favorite_24,
        Constants.SCREEN_NAME_FAVORITES
    ),
    SEARCH(
       searchNavigationRoute,
        R.drawable.ic_baseline_search_24,
        Constants.SCREEN_NAME_SEARCH
    ),
    SETTINGS(
        settingsNavigationRoute,
        R.drawable.ic_baseline_settings,
        Constants.SCREEN_NAME_SETTINGS
    )
}