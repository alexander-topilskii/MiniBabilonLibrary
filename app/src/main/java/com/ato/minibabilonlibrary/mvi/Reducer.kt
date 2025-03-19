package com.ato.minibabilonlibrary.mvi

import com.ato.minibabilonlibrary.MainState
import com.ato.minibabilonlibrary.mvi.user_actions.Action
import com.ato.minibabilonlibrary.mvi.user_actions.ToggleVisibility
import com.ato.minibabilonlibrary.mvi.user_actions.UpdateAlphabet
import com.ato.minibabilonlibrary.mvi.user_actions.UpdateLineCount
import com.ato.minibabilonlibrary.mvi.user_actions.UpdatePageCount
import com.ato.minibabilonlibrary.mvi.user_actions.UpdateSymbols

class Reducer {
    fun reduce(state: MainState, action: Action): MainState {
        when (action) {
            is UpdateAlphabet -> {
                return state.copy(alphabet = action.newAlphabet)
            }

            is UpdatePageCount -> {
                return state.copy(page = action.newPageCount)
            }

            is UpdateLineCount -> {
                return state.copy(line = action.newLineCount)
            }

            is UpdateSymbols -> {
                return state.copy(symbols = action.newSymbolCount)
            }

            is ToggleVisibility -> {
                return state.copy(isSettingsVisible = !state.isSettingsVisible)
            }
        }
        return state
    }
}