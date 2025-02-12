package com.merttoptas.composebase.data.model

import com.google.gson.Gson
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationResponse,
    val name: String,
    val origin: OriginResponse,
    val species: String,
    val status: Status?,
    val type: String,
    val url: String
)