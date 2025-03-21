package com.ato.minibabilonlibrary.main_mvi.elements

import com.ato.mvicore.Action
import java.math.BigInteger

sealed interface MainAction : Action {

    sealed interface InputActions : MainAction {
        data class UpdateAlphabet(val newAlphabet: String) : InputActions

        data class UpdatePageCount(val newPageCount: Int) : InputActions

        data class UpdateLineCount(val newLineCount: Int) : InputActions

        data class UpdateSymbols(val newSymbolCount: Int) : InputActions

        data object ToggleVisibility : InputActions
    }

    sealed interface LogicActions : MainAction {
        data class ChangeBooksCount(val base: Int, val pow: Int) : LogicActions
    }

}
