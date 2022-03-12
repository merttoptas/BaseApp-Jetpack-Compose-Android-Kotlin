package com.merttoptas.composebase.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by merttoptas on 11.03.2022
 */

@Parcelize
data class LocationResponse(
    val name: String?,
    val url: String?
) : Parcelable
