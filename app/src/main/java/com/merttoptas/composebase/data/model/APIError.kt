package com.merttoptas.composebase.data.model

import androidx.compose.runtime.Stable

/**
 * Created by merttoptas on 12.03.2022
 */

@Stable
data class APIError(val code: Long, val message: String)
