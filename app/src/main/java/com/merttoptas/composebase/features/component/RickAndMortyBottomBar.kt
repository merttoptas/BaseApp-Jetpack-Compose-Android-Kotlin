package com.merttoptas.composebase.features.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .clip(RoundedCornerShape(0.dp, 15.dp, 0.dp, 0.dp)),
        cutoutShape = CircleShape,
        backgroundColor = Color.White
    ) {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            elevation = 0.dp,
            backgroundColor = Color.White
        ) {
            BottomNav.values().forEach { screen ->
                BottomNavigationItem(
                    alwaysShowLabel = true,
                    selectedContentColor = Color(0xff2573FA),
                    unselectedContentColor = Color(0xff727272),
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = screen.IconId),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },

                    label = {
                        Text(
                            text = screen.screenName,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp,

                                ),
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                NavScreen.Dashboard.route.let { dashboardRoute ->
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(dashboardRoute) {
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