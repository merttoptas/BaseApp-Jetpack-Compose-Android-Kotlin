package com.merttoptas.composebase.domain.viewstate.characters

import androidx.paging.PagingData
import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 13.03.2022
 */
data class CharactersViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<CharacterDto>>? = null,
    val data: List<Result>? = null,
) : IViewState