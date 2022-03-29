package com.merttoptas.composebase.domain.usecase.favorite

import android.util.Log
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.data.model.dto.extension.toFavoriteEntity
import com.merttoptas.composebase.domain.base.BaseFavoriteUseCase
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by merttoptas on 27.03.2022
 */

class UpdateFavoriteUseCase(
    internal val repository: CharacterRepository
) : BaseFavoriteUseCase<UpdateFavoriteUseCase.Params, Unit>() {

    data class Params(
        val character: CharacterDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.character
        val character = repository.getFavorite(dto.id ?: 0)
        if (character == null) {
            repository.saveFavorite(dto.toFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.id ?: 0)
        }
        emit(Unit)
    }
}