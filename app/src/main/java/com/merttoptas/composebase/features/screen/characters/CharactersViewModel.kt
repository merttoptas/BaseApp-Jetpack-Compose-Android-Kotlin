package com.merttoptas.composebase.features.screen.characters

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.merttoptas.composebase.data.remote.utils.DataState
import com.merttoptas.composebase.domain.repository.CharacterRepository
import com.merttoptas.composebase.domain.viewstate.IViewEvent
import com.merttoptas.composebase.domain.viewstate.characters.CharactersViewState
import com.merttoptas.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 13.03.2022
 */

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel<CharactersViewState, CharactersViewEvent>() {

    init {
        //TODO: check if user is logged in
        viewModelScope.launch {
            delay(1000)
            characterRepository.getAllCharacters(1).collect {
                when (it) {
                    is DataState.Success -> {
                        Log.d("deneme", it.data.results.toString())
                        setState { currentState.copy(data = it.data.results) }
                    }
                    is DataState.Error -> {
                        //  setViewState(CharactersViewState(it.error))
                    }
                    is DataState.Loading -> {
                        Log.d("deneme1", "loading")
                    }
                }
            }

        }
    }

    override fun createInitialState() = CharactersViewState()
}

sealed class CharactersViewEvent : IViewEvent {
    object DirectToLogin : CharactersViewEvent()
    object DirectToDashBoard : CharactersViewEvent()
}