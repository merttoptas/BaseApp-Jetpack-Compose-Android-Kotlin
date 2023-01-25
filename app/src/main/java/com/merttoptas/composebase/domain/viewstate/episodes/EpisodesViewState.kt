package com.merttoptas.composebase.domain.viewstate.episodes

import androidx.compose.runtime.Stable
import com.merttoptas.composebase.data.model.EpisodesResultResponse
import com.merttoptas.composebase.domain.viewstate.IViewState

/**
 * Created by merttoptas on 19.03.2022
 */

@Stable
data class EpisodesViewState(
    val isLoading: Boolean = false,
    val data: List<EpisodesResultResponse>? = null
) : IViewState