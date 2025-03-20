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
        when (action) {
            is UpdateAlphabet,
            is UpdateLineCount,
            is UpdateSymbols,
            is UpdatePageCount -> {
                val newBooksCount = paramsCalculator
                    .calculateBookCount(
                        alphabet = state.alphabet,
                        line = state.linesOnPage.toString(),
                        symbolsOnLine = state.symbolsOnLine.toString(),
                        page = state.pageCount.toString()
                    )

                dispatch(ChangeBooksCount(newBooksCount))
            }

            else -> Unit
        }
    }


}

