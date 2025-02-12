package com.merttoptas.composebase.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Created by merttoptas on 19.03.2022
 */

@Serializable
data class EpisodesResultResponse(
    val id: Int?,
    val name: String?,
    @SerializedName("air_date")
    val airDate: String?,
    val episode: String?,
    val characters: List<String>?,
    val created: String?,
    val url: String?
)  {


    companion object {
        fun create(jsonString: String): EpisodesResultResponse? {
            return try {
                Gson().fromJson(jsonString, EpisodesResultResponse::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}