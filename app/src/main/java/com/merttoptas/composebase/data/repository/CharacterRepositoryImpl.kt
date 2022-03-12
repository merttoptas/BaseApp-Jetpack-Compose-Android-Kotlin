package com.merttoptas.composebase.data.repository

import com.merttoptas.composebase.data.model.CharacterInfoResponse
import com.merttoptas.composebase.data.model.CharacterResponse
import com.merttoptas.composebase.data.remote.source.CharacterRemoteDataSource
import com.merttoptas.composebase.data.remote.utils.DataState
import com.merttoptas.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by merttoptas on 12.03.2022
 */

class CharacterRepositoryImpl @Inject constructor(private val characterRemoteDataSource: CharacterRemoteDataSource) :
    CharacterRepository {

    override fun getAllCharacters(page: Int): Flow<DataState<CharacterResponse>> = flow {
        emitAll(characterRemoteDataSource.getAllCharacters(page = page))
    }

    override fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getCharacter(characterId = characterId))
    }

    override fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getCharacter(url = url))
    }
}