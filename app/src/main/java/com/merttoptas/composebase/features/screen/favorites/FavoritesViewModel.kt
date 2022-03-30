package com.merttoptas.composebase.features.screen.favorites

import androidx.lifecycle.viewModelScope
import com.merttoptas.composebase.domain.usecase.favorite.DeleteFavoriteUseCase
import com.merttoptas.composebase.domain.usecase.favorite.GetFavoritesUseCase
import com.merttoptas.composebase.domain.viewstate.IViewEvent
import com.merttoptas.composebase.domain.viewstate.favorites.FavoritesViewState
import com.merttoptas.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 30.03.2022
 */

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : BaseViewModel<FavoritesViewState, IViewEvent>() {

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            val favoritesList = getFavoritesUseCase.repository.getFavoriteList()
            setState { currentState.copy(favoritesList = favoritesList, isLoading = false) }
        }
    }

    fun onDisplayChange(isDisplay: Boolean, favoriteId: Int?) {
        setState { currentState.copy(isDisplay = isDisplay, favoriteId = favoriteId) }
    }

    fun onDeleteFavorite() {
        viewModelScope.launch {
            currentState.favoriteId?.let {
                call(deleteFavoriteUseCase(DeleteFavoriteUseCase.Params(it)))
                updateFavoriteList()
            }
        }
    }

    private fun updateFavoriteList() {
        viewModelScope.launch {
            val favoritesList = getFavoritesUseCase.repository.getFavoriteList()
            setState { currentState.copy(favoritesList = favoritesList, isLoading = false) }
        }
    }

    override fun createInitialState() = FavoritesViewState()
}
