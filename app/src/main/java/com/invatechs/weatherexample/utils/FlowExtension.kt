package com.invatechs.weatherexample.utils

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenStarted(lifecycle: LifecycleCoroutineScope) {
    lifecycle.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}