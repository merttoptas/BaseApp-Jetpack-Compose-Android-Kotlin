package com.merttoptas.composebase.domain.usecase.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.base.BaseUseCase
import com.merttoptas.composebase.domain.base.IParams
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 27.03.2022
 */

class GetCharactersUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<GetCharactersUseCase.Params, PagingData<CharacterDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CharacterPagingSource(repository, param.options) }
        ).flow
    }
}