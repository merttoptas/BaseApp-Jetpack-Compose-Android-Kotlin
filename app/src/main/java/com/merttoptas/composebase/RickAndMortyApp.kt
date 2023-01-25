package com.merttoptas.composebase

import android.app.Application
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by merttoptas on 13.03.2022
 */

@Stable
@HiltAndroidApp
class RickAndMortyApp : Application() {

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}