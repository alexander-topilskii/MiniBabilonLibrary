package com.ato.minibabilonlibrary

import androidx.lifecycle.ViewModel
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction.InputActions.*

import com.ato.minibabilonlibrary.main_mvi.elements.MainEffect
import com.ato.minibabilonlibrary.main_mvi.elements.MainState
import com.ato.minibabilonlibrary.main_mvi.MainStore
import com.ato.minibabilonlibrary.main_mvi.ui.UiMainState
import com.ato.mvicore.Store
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(

) : ViewModel() {
    private val store: Store<
            MainState,
            MainAction,
            MainEffect,
            UiMainState
            > = MainStore()

    val state: StateFlow<UiMainState> get() = store.uiState

    fun updateAlphabet(newAlphabet: String) {
        store.dispatch(UpdateAlphabet(newAlphabet))
    }

    fun updatePage(newPage: String) {
        store.dispatch(UpdatePageCount(newPage.toInt()))
    }

    fun updateLine(newLine: String) {
        store.dispatch(UpdateLineCount(newLine.toInt()))
    }

    fun updateSymbols(newSymbols: String) {
        store.dispatch(UpdateSymbols(newSymbols.toInt()))
    }

    fun toggleVisibility() {
        store.dispatch(ToggleVisibility)
    }
}
