package com.merttoptas.composebase.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by merttoptas on 10.03.2022
 */

@Parcelize
data class CharacterResponse(
    val id: Int,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val image: String,
    val url: String,
    val origin: OriginResponse,
    val location: LocationResponse?,
    val episode: List<String>
) : Parcelable