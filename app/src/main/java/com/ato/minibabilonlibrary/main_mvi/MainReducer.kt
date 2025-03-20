package com.ato.minibabilonlibrary.main_mvi

import com.ato.minibabilonlibrary.main_mvi.elements.MainAction
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction.*
import com.ato.mvicore.Reducer
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction.InputActions.*
import com.ato.minibabilonlibrary.main_mvi.elements.MainState


class MainReducer : Reducer<MainState, MainAction> {

    override fun reduce(state: MainState, action: MainAction): MainState {
        when (action) {
            is UpdateAlphabet -> {
                return state.copy(alphabet = action.newAlphabet)
            }

            is UpdatePageCount -> {
                return state.copy(pageCount = action.newPageCount)
            }

            is UpdateLineCount -> {
                return state.copy(linesOnPage = action.newLineCount)
            }

            is UpdateSymbols -> {
                return state.copy(symbolsOnLine = action.newSymbolCount)
            }

            is ToggleVisibility -> {
                return state.copy(isSettingsVisible = !state.isSettingsVisible)
            }

            is LogicActions.ChangeBooksCount -> {
                return state.copy(booksCountInLibrary = action.count)
            }

            else -> return state
        }
    }
}