package com.merttoptas.composebase.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by merttoptas on 27.03.2022
 */

@Parcelize
data class LocationDto(
    val locationId: Int,
    val name: String,
    val url: String
) : Parcelable