package com.merttoptas.composebase.domain.viewstate.episodes

import com.merttoptas.composebase.data.model.EpisodesResultResponse
import com.merttoptas.composebase.domain.viewstate.IViewState

/**
 * Created by merttoptas on 19.03.2022
 */

data class EpisodesViewState(
    val isLoading: Boolean = false,
    val data: List<EpisodesResultResponse>? = null
) : IViewState