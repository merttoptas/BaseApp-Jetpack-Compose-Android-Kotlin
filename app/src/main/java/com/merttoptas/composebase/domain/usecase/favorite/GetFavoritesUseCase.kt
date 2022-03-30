package com.merttoptas.composebase.domain.usecase.favorite

import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.data.model.dto.extension.toFavoriteDtoList
import com.merttoptas.composebase.domain.base.BaseFavoriteUseCase
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by merttoptas on 30.03.2022
 */

class GetFavoritesUseCase(
    internal val repository: CharacterRepository
) : BaseFavoriteUseCase<Unit, List<CharacterDto>>() {

    override suspend fun FlowCollector<List<CharacterDto>>.execute(params: Unit) {
        val favorites = repository.getFavoriteList()
        emit(favorites.toFavoriteDtoList())
    }
}