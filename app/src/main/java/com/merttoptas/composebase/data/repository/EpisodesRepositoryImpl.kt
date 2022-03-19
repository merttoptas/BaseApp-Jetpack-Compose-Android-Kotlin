package com.merttoptas.composebase.data.repository

import com.merttoptas.composebase.data.model.EpisodesResponse
import com.merttoptas.composebase.data.remote.source.EpisodesRemoteDataSource
import com.merttoptas.composebase.data.remote.utils.DataState
import com.merttoptas.composebase.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by merttoptas on 19.03.2022
 */

class EpisodesRepositoryImpl @Inject constructor(private val episodesRemoteDataSource: EpisodesRemoteDataSource) :
    EpisodesRepository {

    override fun getAllEpisodes(): Flow<DataState<EpisodesResponse>> = flow {
        emitAll(episodesRemoteDataSource.getAllEpisodes())
    }

    override fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>> = flow {
        emitAll(episodesRemoteDataSource.getEpisode(episodeId))
    }
}