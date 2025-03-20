package com.ato.minibabilonlibrary

import androidx.lifecycle.ViewModel
import com.ato.minibabilonlibrary.main_mvi.MainAction
import com.ato.minibabilonlibrary.main_mvi.MainAction.ToggleVisibility
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdateAlphabet
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdateLineCount
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdatePageCount
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdateSymbols
import com.ato.minibabilonlibrary.main_mvi.MainEffect
import com.ato.minibabilonlibrary.main_mvi.MainStore
import com.ato.minibabilonlibrary.mvi.Store
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val store: Store<MainState, MainAction, MainEffect> = MainStore()
) : ViewModel() {

    val state: StateFlow<MainState> get() = store.state

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
