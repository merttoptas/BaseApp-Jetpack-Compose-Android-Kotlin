package com.merttoptas.composebase.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 27.03.2022
 */

object Utility {

    inline fun <reified T> T.toJson(): String {
        return try {
            Gson().toJson(this)
        } catch (ex: Exception) {
            ""
        }
    }

    inline fun <reified T> String.fromJson(): T? {
        return try {
            Gson().fromJson(this, T::class.java)
        } catch (ex: Exception) {
            null
        }
    }

    fun Activity.launchActivity(
        packageName: String,
        className: String,
        flags: Int = -1,
        bundle: Bundle? = null
    ) {
        val intent = Intent(Intent.ACTION_VIEW).setClassName(packageName, className)
        if (flags != -1) {
            intent.flags = flags
        }
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    @Composable
    fun <T> rememberFlowWithLifecycle(
        flow: Flow<T>,
        lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED
    ): Flow<T> = remember(flow, lifecycle) {
        flow.flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = minActiveState
        )
    }

    @Composable
    fun getAnimateFloat(): State<Float> {
        val infiniteTransition = rememberInfiniteTransition()
        return infiniteTransition.animateFloat(
            initialValue = 24.0f,
            targetValue = 48.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    delayMillis = 100,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
}