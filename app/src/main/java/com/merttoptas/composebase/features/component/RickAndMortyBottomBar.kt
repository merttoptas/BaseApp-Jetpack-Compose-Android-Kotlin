package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortyBottomAppBar(
    navController: NavController,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
        content = {
            BottomNav.values().forEach { bottomNav ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(bottomNav.iconId),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(bottomNav.titleTextId),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    selected = currentDestination.isBottomNavDestinationInHierarchy(bottomNav),
                    onClick = {
                        navigateToBottomNavDestination(bottomNav, navController)
                    }
                )
            }
        }
    )
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
