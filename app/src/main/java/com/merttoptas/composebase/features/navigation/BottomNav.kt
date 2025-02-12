package com.merttoptas.composebase.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.merttoptas.composebase.R
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.features.screen.characters.navigation.Characters
import com.merttoptas.composebase.features.screen.episodes.navigation.Episodes
import com.merttoptas.composebase.features.screen.favorites.navigation.Favorites
import com.merttoptas.composebase.features.screen.search.navigation.Search
import com.merttoptas.composebase.features.screen.settings.navigation.Settings

/**
 * Created by merttoptas on 9.03.2022
 */

enum class BottomNav(
    val route: Route,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    CHARACTERS(
        Characters,
        R.drawable.ic_outline_people,
        R.string.characters_screen_title,
    ),
    EPISODES(
        Episodes,
        R.drawable.ic_baseline_movie_creation_24,
        R.string.episodes_screen_title
    ),
    FAVORITES(
        Favorites,
        R.drawable.ic_baseline_favorite_24,
        R.string.favorite_screen_title,
    ),
    SEARCH(
        Search,
        R.drawable.ic_baseline_search_24,
        R.string.search_screen_title,
    ),
    SETTINGS(
        Settings,
        R.drawable.ic_baseline_settings,
        R.string.settings_screen_title
    )
}