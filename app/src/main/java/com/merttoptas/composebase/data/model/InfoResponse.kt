package com.merttoptas.composebase.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)