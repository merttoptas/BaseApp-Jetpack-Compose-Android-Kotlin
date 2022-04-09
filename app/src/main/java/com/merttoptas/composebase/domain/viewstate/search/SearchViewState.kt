package com.merttoptas.composebase.domain.viewstate.search

import com.merttoptas.composebase.domain.viewstate.IViewState

/**
 * Created by merttoptas on 9.04.2022
 */

data class SearchViewState(
    val isLoading: Boolean = false
) : IViewState