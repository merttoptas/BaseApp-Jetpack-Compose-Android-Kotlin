package com.merttoptas.composebase.domain.viewstate.search

import android.os.Parcelable
import androidx.paging.PagingData
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

/**
 * Created by merttoptas on 9.04.2022
 */

data class SearchViewState(
    val searchText: String? = null,
    val pagedData: Flow<PagingData<CharacterDto>>? = null,
    val status: List<CharacterStatus> = listOf(
        CharacterStatus(Status.Alive.value, false),
        CharacterStatus(Status.Dead.value, false),
        CharacterStatus(Status.Unknown.value, false)
    ),
    val gender: List<CharacterGender> = listOf(
        CharacterGender("female", false),
        CharacterGender("male", false),
        CharacterGender("genderless", false),
        CharacterGender("unknown", false)
    ),
    val isLoading: Boolean = false
) : IViewState

@Parcelize
data class CharacterStatus(
    val name: String,
    val selected: Boolean,
) : Parcelable

@Parcelize
data class CharacterGender(
    val name: String,
    val selected: Boolean,
) : Parcelable
