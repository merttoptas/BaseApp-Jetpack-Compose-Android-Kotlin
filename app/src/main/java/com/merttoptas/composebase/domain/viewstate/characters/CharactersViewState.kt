package com.merttoptas.composebase.domain.viewstate.characters

import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.domain.viewstate.IViewState

/**
 * Created by merttoptas on 13.03.2022
 */
data class CharactersViewState(
    val isLoading: Boolean = false,
    val data: List<Result>? = null
) : IViewState