package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.fillMaxWidth
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
import com.merttoptas.composebase.features.navigation.BottomNav
import com.merttoptas.composebase.features.navigation.NavScreen

/**
 * Created by merttoptas on 12.03.2022
 */

@Composable
fun RickAndMortyBottomAppBar(
    navController: NavController,
    currentRoute: String?
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
        ) {
            BottomNav.values().forEach { screen ->
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
                            text = screen.screenName,
                            style = MaterialTheme.typography.overline,
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                NavScreen.Characters.route.let { charactersRoute ->
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(charactersRoute) {
                                        saveState = true
                                    }
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}