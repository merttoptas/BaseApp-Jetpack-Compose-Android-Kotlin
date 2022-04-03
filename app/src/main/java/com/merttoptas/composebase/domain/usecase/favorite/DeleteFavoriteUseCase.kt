package com.merttoptas.composebase.domain.usecase.favorite

import com.merttoptas.composebase.domain.base.BaseFavoriteUseCase
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector

/**
 * Created by merttoptas on 29.03.2022
 */

class DeleteFavoriteUseCase(
    internal val repository: CharacterRepository
) : BaseFavoriteUseCase<DeleteFavoriteUseCase.Params, Unit>() {

    data class Params(
        val characterId: Int?
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        params.characterId?.let {
            repository.deleteFavoriteById(params.characterId)
        } ?: kotlin.run {
            repository.deleteFavoriteList()
        }
        emit(Unit)
    }
}