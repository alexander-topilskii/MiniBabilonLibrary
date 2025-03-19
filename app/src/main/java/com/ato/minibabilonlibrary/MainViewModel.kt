package com.ato.minibabilonlibrary

import androidx.lifecycle.ViewModel
import com.ato.minibabilonlibrary.mvi.Store
import com.ato.minibabilonlibrary.mvi.user_actions.ToggleVisibility
import com.ato.minibabilonlibrary.mvi.user_actions.UpdateAlphabet
import com.ato.minibabilonlibrary.mvi.user_actions.UpdateLineCount
import com.ato.minibabilonlibrary.mvi.user_actions.UpdatePageCount
import com.ato.minibabilonlibrary.mvi.user_actions.UpdateSymbols
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    val store: Store = Store()
) : ViewModel() {

    val state: StateFlow<MainState> get() = store._state

    fun updateAlphabet(newAlphabet: String) {
        store.dispatch(UpdateAlphabet(newAlphabet))
    }

    fun updatePage(newPage: String) {
        store.dispatch(UpdatePageCount(newPage))
    }

    fun updateLine(newLine: String) {
        store.dispatch(UpdateLineCount(newLine))
    }

    fun updateSymbols(newSymbols: String) {
        store.dispatch(UpdateSymbols(newSymbols))
    }

    fun toggleVisibility() {
        store.dispatch(ToggleVisibility)
    }
}
