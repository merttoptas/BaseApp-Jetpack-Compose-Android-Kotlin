package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.merttoptas.composebase.features.navigation.BottomNav
import com.merttoptas.composebase.features.screen.characters.navigation.navigateCharacter
import com.merttoptas.composebase.features.screen.episodes.navigation.navigateToEpisodes
import com.merttoptas.composebase.features.screen.favorites.navigation.navigateToFavorites
import com.merttoptas.composebase.features.screen.search.navigation.navigateToSearch
import com.merttoptas.composebase.features.screen.settings.navigation.navigateToSettings
import com.merttoptas.composebase.utils.Constants

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortyBottomAppBar(
    navController: NavController,
    currentRoute: String?,
    currentDestination: NavDestination?,
) {
    BottomAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                )
            ),
        cutoutShape = CircleShape,
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.onSecondary
    ) {
        BottomNav.values().forEach { screen ->
            val selected = currentDestination.isBottomNavDestinationInHierarchy(screen)

            BottomNavigationItem(
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },

                label = {
                    RickAndMortyText(
                        text = if (screen.screenName == Constants.SCREEN_NAME_FAVORITES) "" else screen.screenName,
                        style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.Center
                    )
                },
                selected = selected,
                onClick = {
                    navigateToBottomNavDestination(screen, navController)
                }
            )
        }
    }
}

fun navigateToBottomNavDestination(bottomNav: BottomNav, navController: NavController) {
    trace("Navigation: ${bottomNav.name}") {
        val bottomNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (bottomNav) {
            BottomNav.CHARACTERS -> navController.navigateCharacter(bottomNavOptions)
            BottomNav.EPISODES -> navController.navigateToEpisodes(bottomNavOptions)
            BottomNav.FAVORITES -> navController.navigateToFavorites(bottomNavOptions)
            BottomNav.SEARCH -> navController.navigateToSearch(bottomNavOptions)
            BottomNav.SETTINGS -> navController.navigateToSettings(bottomNavOptions)
        }
    }
}

private fun NavDestination?.isBottomNavDestinationInHierarchy(destination: BottomNav) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
