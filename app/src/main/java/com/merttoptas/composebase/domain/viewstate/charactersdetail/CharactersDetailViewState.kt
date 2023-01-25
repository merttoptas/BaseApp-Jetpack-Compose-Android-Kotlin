package com.merttoptas.composebase.domain.viewstate.charactersdetail

import androidx.compose.runtime.Stable
import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.domain.viewstate.IViewState

/**
 * Created by merttoptas on 13.03.2022
 */
@Stable
data class CharactersDetailViewState(
    val isLoading: Boolean = false,
    val data: Result? = null
) : IViewState