package com.merttoptas.composebase.features.screen.splash

import androidx.lifecycle.viewModelScope
import com.merttoptas.composebase.domain.viewstate.IViewEvent
import com.merttoptas.composebase.domain.viewstate.splash.SplashViewState
import com.merttoptas.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 12.03.2022
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    init {
        //TODO: check if user is logged in
        viewModelScope.launch {
            delay(1000)
            setEvent(SplashViewEvent.DirectToDashBoard)

        }
    }

    override fun createInitialState() = SplashViewState()
}

sealed class SplashViewEvent : IViewEvent {
    object DirectToLogin : SplashViewEvent()
    object DirectToDashBoard : SplashViewEvent()
}