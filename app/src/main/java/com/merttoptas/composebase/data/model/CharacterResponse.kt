package com.merttoptas.composebase.data.model

import kotlinx.serialization.Serializable

/**
 * Created by merttoptas on 10.03.2022
 */

@Serializable
data class CharacterResponse(
    val info: InfoResponse,
    val results: List<Result>
)