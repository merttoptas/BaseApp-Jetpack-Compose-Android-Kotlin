package com.merttoptas.composebase.data.remote.source.impl

import com.merttoptas.composebase.data.model.CharacterInfoResponse
import com.merttoptas.composebase.data.model.CharacterResponse
import com.merttoptas.composebase.data.remote.api.CharacterService
import com.merttoptas.composebase.data.remote.source.CharacterRemoteDataSource
import com.merttoptas.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by merttoptas on 12.03.2022
 */
class CharacterRemoteDataSourceImpl @Inject constructor(private val characterService: CharacterService) :
    BaseRemoteDataSource(), CharacterRemoteDataSource {

    override suspend fun getAllCharacters(page: Int, options: Map<String, String>): CharacterResponse {
        return characterService.getAllCharacters(page, options)
    }

    override suspend fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>> =
        getResult { characterService.getCharacter(characterId = characterId) }

    override suspend fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>> =
        getResult {
            characterService.getCharacter(url)
        }
}
