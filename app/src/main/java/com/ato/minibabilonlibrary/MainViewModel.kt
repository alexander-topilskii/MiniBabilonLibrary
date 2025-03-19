package com.ato.minibabilonlibrary

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState("kek"))
    val state: StateFlow<MainState> get() = _state

    fun updateText(newText: String) {
        _state.value = MainState(newText)
    }

    fun updateAlphabet(newAlphabet: String) {
        _state.value = _state.value.copy(alphabet = newAlphabet)
    }

    fun updatePage(newPage: String) {
        _state.value = _state.value.copy(page = newPage)
    }

    fun updateLine(newLine: String) {
        _state.value = _state.value.copy(line = newLine)
    }

    fun updateSymbols(newSymbols: String) {
        _state.value = _state.value.copy(symbols = newSymbols)
    }


    fun toggleVisibility() {
        _state.value = _state.value.copy(isSettingsVisible = !_state.value.isSettingsVisible)
    }
}