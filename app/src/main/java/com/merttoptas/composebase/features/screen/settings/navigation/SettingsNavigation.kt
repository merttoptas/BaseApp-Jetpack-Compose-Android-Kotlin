@file:OptIn(ExperimentalAnimationApi::class)

package com.merttoptas.composebase.features.screen.settings.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.features.screen.settings.SettingsScreen
import kotlinx.serialization.Serializable

/**
 * Created by mertcantoptas on 23.01.2023
 */

@Serializable
data object Settings : Route()

fun NavController.navigateToSettings(
    navOptions: NavOptions? = null
) {
    this.navigate(Settings, navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable<Settings> {
        SettingsScreen()
    }
}