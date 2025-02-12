package com.merttoptas.composebase.features.screen.episodes.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.features.screen.episodes.EpisodesScreen
import kotlinx.serialization.Serializable

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val episodesNavigationRoute = "episodes_route"

@Serializable
data object Episodes : Route()

fun NavController.navigateToEpisodes(
    navOptions: NavOptions? = null
) {
    this.navigate(Episodes, navOptions)
}

fun NavGraphBuilder.episodesScreen() {
    composable<Episodes> {
        EpisodesScreen(
            hiltViewModel()
        )
    }
}