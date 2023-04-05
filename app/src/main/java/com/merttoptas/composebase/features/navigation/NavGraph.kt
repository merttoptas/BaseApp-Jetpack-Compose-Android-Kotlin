package com.merttoptas.composebase.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.merttoptas.composebase.features.component.RickAndMortyBottomAppBar
import com.merttoptas.composebase.features.component.RickAndMortyFloatingActionBar
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.screen.characters.navigation.charactersNavigationRoute
import com.merttoptas.composebase.features.screen.characters.navigation.charactersScreen
import com.merttoptas.composebase.features.screen.charactersdetail.navigation.charactersDetailScreen
import com.merttoptas.composebase.features.screen.charactersdetail.navigation.navigateCharactersDetail
import com.merttoptas.composebase.features.screen.episodes.navigation.episodesScreen
import com.merttoptas.composebase.features.screen.favorites.navigation.favoritesScreen
import com.merttoptas.composebase.features.screen.search.navigation.searchScreen
import com.merttoptas.composebase.features.screen.settings.navigation.settingsScreen
import com.merttoptas.composebase.utils.Utility.toJson

/**
 * Created by merttoptas on 9.03.2022
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination

    RickAndMortyScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyBottomAppBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyFloatingActionBar(
                        navController = navController,
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = charactersNavigationRoute,
        ) {
            charactersScreen { navController.navigateCharactersDetail(it.toJson()) }
            charactersDetailScreen { navController.navigateUp() }
            episodesScreen()
            searchScreen { navController.navigateCharactersDetail(it.toJson()) }
            settingsScreen()
            favoritesScreen { navController.navigateCharactersDetail(it.toJson()) }
        }
    }
}
