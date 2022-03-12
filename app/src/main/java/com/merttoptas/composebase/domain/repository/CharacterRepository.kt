package com.merttoptas.composebase.domain.repository

import com.merttoptas.composebase.data.model.CharacterInfoResponse
import com.merttoptas.composebase.data.model.CharacterResponse
import com.merttoptas.composebase.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 12.03.2022
 */

interface CharacterRepository {
    fun getAllCharacters(page: Int): Flow<DataState<CharacterResponse>>
    fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>>
    fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>>
}