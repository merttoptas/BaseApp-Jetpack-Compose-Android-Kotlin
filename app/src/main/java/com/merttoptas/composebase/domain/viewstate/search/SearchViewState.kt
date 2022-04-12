package com.merttoptas.composebase.domain.viewstate.search

import android.os.Parcelable
import com.merttoptas.composebase.data.model.Status
import com.merttoptas.composebase.domain.viewstate.IViewState
import kotlinx.parcelize.Parcelize

/**
 * Created by merttoptas on 9.04.2022
 */

data class SearchViewState(
    val searchText: String? = null,
    val status: List<CharacterStatus> = listOf(
        CharacterStatus(Status.Alive.value, false),
        CharacterStatus(Status.Dead.value, false),
        CharacterStatus(Status.Unknown.value, false)
    ),
    val gender: List<CharacterGender> = listOf(
        CharacterGender("Female", false),
        CharacterGender("Male", false),
        CharacterGender("Genderless", false),
        CharacterGender("Unknown", false)
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