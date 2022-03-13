package com.merttoptas.composebase.domain.viewstate.charactersdetail

import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.domain.viewstate.IViewState

/**
 * Created by merttoptas on 13.03.2022
 */

data class CharactersDetailViewState(
    val isLoading: Boolean = false,
    val data: Result? = null
) : IViewState