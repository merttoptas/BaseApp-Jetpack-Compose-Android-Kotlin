package com.merttoptas.composebase.domain.base

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by merttoptas on 27.03.2022
 */

abstract class BaseUseCase<in Params, ReturnType> where ReturnType : Any {

    protected abstract fun execute(params: Params): Flow<PagingData<ReturnType>>

    operator fun invoke(params: Params): Flow<PagingData<ReturnType>> = execute(params)
        .flowOn(Dispatchers.IO)
}