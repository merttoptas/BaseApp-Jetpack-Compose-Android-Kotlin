package com.merttoptas.composebase.data.remote.source

import com.merttoptas.composebase.data.model.CharacterInfoResponse
import com.merttoptas.composebase.data.model.CharacterResponse
import com.merttoptas.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 12.03.2022
 */

interface CharacterRemoteDataSource {
   suspend fun getAllCharacters(page: Int, options: Map<String, String>): CharacterResponse
    suspend fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>>
    suspend fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>>
}