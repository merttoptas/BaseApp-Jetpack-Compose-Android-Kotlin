package com.merttoptas.composebase.features.screen.charactersdetail

import androidx.lifecycle.SavedStateHandle
import com.merttoptas.composebase.data.model.Result
import com.merttoptas.composebase.domain.viewstate.IViewEvent
import com.merttoptas.composebase.domain.viewstate.charactersdetail.CharactersDetailViewState
import com.merttoptas.composebase.features.base.BaseViewModel
import com.merttoptas.composebase.features.screen.charactersdetail.navigation.CharactersDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by merttoptas on 13.03.2022
 */
@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CharactersDetailViewState, CharactersDetailViewEvent>() {

    init {

    }

    override fun createInitialState() = CharactersDetailViewState()
    override fun onTriggerEvent(event: CharactersDetailViewEvent) {}
}

sealed class CharactersDetailViewEvent : IViewEvent {
    class SnackBarMessage(val message: String?) : CharactersDetailViewEvent()
}