package com.merttoptas.composebase.data.model

import kotlinx.serialization.Serializable

/**
 * Created by merttoptas on 11.03.2022
 */

@Serializable
data class OriginResponse(
    val name: String?,
    val url: String?,
)