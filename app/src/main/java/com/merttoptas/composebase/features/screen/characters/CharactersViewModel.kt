package com.merttoptas.composebase.features.screen.characters

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.merttoptas.composebase.domain.usecase.GetCharactersUseCase
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
    private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel<CharactersViewState, CharactersViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            val params = GetCharactersUseCase.Params(config, hashMapOf())
            val pagedFlow = getCharactersUseCase(params).cachedIn(scope = viewModelScope)

            Log.d("deneme1", "pagedFlow: ${pagedFlow.toString()}")
            setState { currentState.copy(pagedData = pagedFlow) }
            setState { currentState.copy(isLoading = false) }

        }
    }

    override fun createInitialState() = CharactersViewState()
}

sealed class CharactersViewEvent : IViewEvent {
    object DirectToLogin : CharactersViewEvent()
    object DirectToDashBoard : CharactersViewEvent()
}