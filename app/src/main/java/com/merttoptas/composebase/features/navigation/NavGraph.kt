package com.merttoptas.composebase.features.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination

    RickAndMortyScaffold(
        bottomBar = {
            BottomNav.entries.forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyBottomAppBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.entries.forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyFloatingActionBar(
                        navController = navController,
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = charactersNavigationRoute,
            Modifier.padding(innerPadding)
        ) {
            charactersScreen { navController.navigateCharactersDetail(it) }
            charactersDetailScreen { navController.navigateUp() }
            episodesScreen()
            searchScreen { navController.navigateCharactersDetail(it.toJson()) }
            settingsScreen()
            favoritesScreen { navController.navigateCharactersDetail(it.toJson()) }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomNav) =
    this?.hierarchy?.any { it.route?.contains(destination.route.toString(), true) ?: false }
        ?: false

