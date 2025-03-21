package com.ato.minibabilonlibrary.main_mvi.middleware

import com.ato.minibabilonlibrary.main_mvi.elements.MainState
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction.InputActions.*
import com.ato.minibabilonlibrary.main_mvi.elements.MainAction.LogicActions.ChangeBooksCount
import com.ato.minibabilonlibrary.main_mvi.logic.LibraryParamsCalculator
import com.ato.mvicore.Middleware

class LibrarySettingsMiddleware(
    private val paramsCalculator: LibraryParamsCalculator = LibraryParamsCalculator
) : Middleware<MainState, MainAction> {

    override fun process(state: MainState, action: MainAction, dispatch: (MainAction) -> Unit) {
        val (base, pow) = when (action) {
            is UpdateAlphabet -> {
                paramsCalculator
                    .calculateBookCount(
                        alphabet = action.newAlphabet,
                        line = state.linesOnPage.toString(),
                        symbolsOnLine = state.symbolsOnLine.toString(),
                        page = state.pageCount.toString()
                    )
            }

            is UpdateLineCount -> {
                paramsCalculator
                    .calculateBookCount(
                        alphabet = state.alphabet,
                        line = action.newLineCount.toString(),
                        symbolsOnLine = state.symbolsOnLine.toString(),
                        page = state.pageCount.toString()
                    )
            }

            is UpdateSymbols -> {
                paramsCalculator
                    .calculateBookCount(
                        alphabet = state.alphabet,
                        line = state.linesOnPage.toString(),
                        symbolsOnLine = action.newSymbolCount.toString(),
                        page = state.pageCount.toString()
                    )
            }

            is UpdatePageCount -> {
                paramsCalculator
                    .calculateBookCount(
                        alphabet = state.alphabet,
                        line = state.linesOnPage.toString(),
                        symbolsOnLine = state.symbolsOnLine.toString(),
                        page = action.newPageCount.toString()
                    )
            }

            else -> return
        }

        dispatch(ChangeBooksCount(base, pow))
    }
}
