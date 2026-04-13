package com.fuyl.challenge.core.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S, E> : ViewModel() {
    protected val _uiState = MutableStateFlow(initialState())
    open val uiState: StateFlow<S> = _uiState.asStateFlow()

    abstract fun initialState(): S

    abstract fun onEvent(event: E)

    protected fun updateState(reducer: (S) -> S) {
        _uiState.value = reducer(_uiState.value)
    }
}