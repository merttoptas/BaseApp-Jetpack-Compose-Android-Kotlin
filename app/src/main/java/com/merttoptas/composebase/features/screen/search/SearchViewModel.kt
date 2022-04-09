package com.merttoptas.composebase.features.screen.search

import androidx.lifecycle.viewModelScope
import com.merttoptas.composebase.RickAndMortyApp
import com.merttoptas.composebase.domain.viewstate.IViewEvent
import com.merttoptas.composebase.domain.viewstate.search.SearchViewState
import com.merttoptas.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 9.04.2022
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val application: RickAndMortyApp
) : BaseViewModel<SearchViewState, SearchViewEvent>() {

    init {
        setState { currentState.copy(isLoading = application.isDark.value) }
    }

    fun searchText(value: String?) {
        setState { currentState.copy(searchText = value) }
    }

    fun selectGender(value: String) {
        setState { currentState.copy(gender = currentState.gender.map { it.copy(selected = it.name == value && it.selected.not()) }) }
    }

    fun selectStatus(value: String) {
        setState { currentState.copy(status = currentState.status.map { it.copy(selected = it.name == value && it.selected.not()) }) }
    }

    override fun createInitialState() = SearchViewState()
    override fun onTriggerEvent(event: SearchViewEvent) {
        viewModelScope.launch {
            when (event) {
                is SearchViewEvent.NewSearchEvent -> {}

            }
        }
    }
}

sealed class SearchViewEvent : IViewEvent {
    object NewSearchEvent : SearchViewEvent()
}
