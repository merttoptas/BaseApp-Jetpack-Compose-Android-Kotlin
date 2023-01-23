@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.composebase.features.screen.settings.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.merttoptas.composebase.features.screen.settings.SettingsScreen

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val settingsNavigationRoute = "settings_route"

fun NavController.navigateToSettings(
    navOptions: NavOptions? = null
) {
    this.navigate(settingsNavigationRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable(settingsNavigationRoute) {
        SettingsScreen(
            hiltViewModel()
        )
    }
}