package com.merttoptas.composebase.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponse(
    val info: InfoResponse,
    val results: List<EpisodesResultResponse>
)
