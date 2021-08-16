package com.invatechs.weatherexample.utils

import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

fun ViewGroup.animate(duration: Long = 250L, block: () -> Unit) {
    val autoTransition = AutoTransition().apply {
        setDuration(duration)
    }
    TransitionManager.beginDelayedTransition(this, autoTransition)
    block.invoke()
}