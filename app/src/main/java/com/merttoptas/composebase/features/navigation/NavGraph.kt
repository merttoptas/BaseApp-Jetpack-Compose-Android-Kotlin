package com.merttoptas.composebase.features.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.merttoptas.composebase.features.component.RickAndMortyBottomAppBar
import com.merttoptas.composebase.features.component.RickAndMortyFloatingActionBar
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.screen.characters.CharactersScreen
import com.merttoptas.composebase.features.screen.charactersdetail.CharactersDetailScreen
import com.merttoptas.composebase.features.screen.episodes.EpisodesScreen
import com.merttoptas.composebase.features.screen.favorites.FavoritesScreen
import com.merttoptas.composebase.features.screen.search.SearchScreen
import com.merttoptas.composebase.features.screen.settings.SettingsScreen
import com.merttoptas.composebase.utils.Utility.toJson

/**
 * Created by merttoptas on 9.03.2022
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(startDestination: String = NavScreen.Characters.route) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    RickAndMortyScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyBottomAppBar(
                        navController = navController,
                        currentRoute = currentRoute
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
        backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(NavScreen.Characters.route) {
                CharactersScreen(
                    hiltViewModel(),
                    navigateToDetail = {
                        navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${it.toJson()}"))
                    }
                )
            }
            composable(
                NavScreen.CharacterDetail.route.plus("?characterDetail={characterDetail}"),
                content = {
                    CharactersDetailScreen(
                        viewModel = hiltViewModel(),
                        navigateToBack = {
                            navController.popBackStack()
                        }
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            )

            composable(NavScreen.Episodes.route) {
                EpisodesScreen(
                    hiltViewModel()
                )
            }

            composable(NavScreen.Search.route) {
                SearchScreen(
                    hiltViewModel(),
                    navigateToDetail = {
                        navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${it.toJson()}"))
                    }
                )
            }

            composable(NavScreen.Settings.route) {
                SettingsScreen(
                    hiltViewModel()
                )
            }

            composable(
                NavScreen.Favorites.route,
                content = {
                    FavoritesScreen(
                        viewModel = hiltViewModel(),
                        navigateCharacterDetail = {
                            navController.navigate(NavScreen.CharacterDetail.route.plus("?characterDetail=${it.toJson()}"))
                        }
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            )
        }
    }
}