package com.ato.minibabilonlibrary.mvi

import com.ato.minibabilonlibrary.MainState
import com.ato.minibabilonlibrary.mvi.user_actions.Action
import com.ato.minibabilonlibrary.mvi.user_actions.UserAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Store(
    private val reducer: Reducer = Reducer(),
    val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState("kek"))
) {

    fun dispatch(action: Action) {
        _state.value = reducer.reduce(_state.value, action)
    }

    fun dispatch(action: UserAction) {
        _state.value = reducer.reduce(_state.value, action)
    }
}

