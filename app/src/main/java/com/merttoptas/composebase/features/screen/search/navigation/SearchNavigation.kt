package com.merttoptas.composebase.features.screen.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.composebase.common.Route
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.features.screen.search.SearchScreen
import kotlinx.serialization.Serializable

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val searchNavigationRoute = "search_route"

@Serializable
data object Search : Route()

fun NavController.navigateToSearch(
    navOptions: NavOptions? = null
) {
    this.navigate(Search, navOptions)
}

fun NavGraphBuilder.searchScreen(navigateToDetail: (CharacterDto?) -> Unit) {
    composable<Search> {
        SearchScreen(
            navigateToDetail = navigateToDetail
        )
    }
}