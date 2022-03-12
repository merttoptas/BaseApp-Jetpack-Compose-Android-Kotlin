package com.merttoptas.composebase.data.remote.api

import com.merttoptas.composebase.data.model.CharacterInfoResponse
import com.merttoptas.composebase.data.model.CharacterResponse
import com.merttoptas.composebase.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by merttoptas on 10.03.2022
 */

interface CharacterService {

    @GET(Constants.CHARACTER_LIST)
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<CharacterResponse>

    @GET(Constants.GET_CHARACTER)
    suspend fun getCharacter(
        @Path("id") characterId: Int
    ): Response<CharacterInfoResponse>

    @GET(Constants.GET_CHARACTER)
    suspend fun getCharacter(
        @Url url: String
    ): Response<CharacterInfoResponse>
}