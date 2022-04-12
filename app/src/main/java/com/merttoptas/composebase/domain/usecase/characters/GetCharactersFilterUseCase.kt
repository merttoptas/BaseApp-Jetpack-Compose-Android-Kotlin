package com.merttoptas.composebase.domain.usecase.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.merttoptas.composebase.data.model.dto.CharacterDto
import com.merttoptas.composebase.domain.base.BasePaginationUseCase
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 12.04.2022
 */
class GetCharactersFilterUseCase(
    internal val repository: CharacterRepository
) : BasePaginationUseCase<GetCharactersFilterUseCase.Params, CharacterDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

    override fun execute(params: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { CharactersFilterPagingSource(repository, params.options) }
        ).flow
    }
}