package com.merttoptas.composebase.domain.usecase.favorite

import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.data.model.dto.extension.toFavoriteDtoList
import com.merttoptas.composebase.domain.base.BaseUseCase
import com.merttoptas.composebase.domain.base.IParams
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by merttoptas on 30.03.2022
 */

class GetFavoritesUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<IParams, List<CharacterDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toFavoriteDtoList())
    }
}