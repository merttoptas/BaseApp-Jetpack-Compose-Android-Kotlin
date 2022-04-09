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
) : BaseViewModel<SearchViewState, IViewEvent>() {

    init {
        setState { currentState.copy(isLoading = application.isDark.value) }
    }

    fun onChangeTheme() {
        viewModelScope.launch {
            application.toggleTheme()
            setState { currentState.copy(isLoading = application.isDark.value) }
        }
    }

    override fun createInitialState() = SearchViewState()
    override fun onTriggerEvent(event: IViewEvent) {
        TODO("Not yet implemented")
    }
}
