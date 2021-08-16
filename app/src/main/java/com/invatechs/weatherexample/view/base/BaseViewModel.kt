package com.invatechs.weatherexample.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ViewState, Action : ViewAction>(
    initState: State? = null
) : ViewModel() {

    private val _viewState: MutableStateFlow<State?> = MutableStateFlow(initState)
    val viewState: StateFlow<State?> = _viewState

    private val _actionState: MutableStateFlow<Action?> = MutableStateFlow(null)
    val actionState: StateFlow<Action?> = _actionState

    fun reduceState(reducer: (oldState: State) -> State) {
        viewModelScope.launch {
            val oldState = viewState.value!!
            _viewState.tryEmit(reducer(oldState))
        }
    }
}