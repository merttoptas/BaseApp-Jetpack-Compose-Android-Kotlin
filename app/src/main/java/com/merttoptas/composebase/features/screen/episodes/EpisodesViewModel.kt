package com.merttoptas.composebase.features.screen.episodes

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.merttoptas.composebase.data.remote.utils.DataState
import com.merttoptas.composebase.domain.repository.EpisodesRepository
import com.merttoptas.composebase.domain.viewstate.IViewEvent
import com.merttoptas.composebase.domain.viewstate.episodes.EpisodesViewState
import com.merttoptas.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 19.03.2022
 */

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : BaseViewModel<EpisodesViewState, EpisodesViewEvent>() {

    init {
        getAllEpisodes()
    }

    private fun getAllEpisodes() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            episodesRepository.getAllEpisodes().collect {
                when (it) {
                    is DataState.Success -> {
                        setState { currentState.copy(data = it.data.results, isLoading = false) }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(EpisodesViewEvent.SnackBarError(it.apiError?.message))

                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }

                    }
                }
            }

        }
    }

    override fun createInitialState() = EpisodesViewState()
    override fun onTriggerEvent(event: EpisodesViewEvent) {}
}

sealed class EpisodesViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : EpisodesViewEvent()
}