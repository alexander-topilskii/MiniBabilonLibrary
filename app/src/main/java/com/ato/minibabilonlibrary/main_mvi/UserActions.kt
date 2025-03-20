package com.ato.minibabilonlibrary.main_mvi

import com.ato.minibabilonlibrary.mvi.Action
import com.ato.minibabilonlibrary.mvi.Effect


sealed interface MainAction: Action {

    data class UpdateAlphabet(val newAlphabet: String): MainAction

    data class UpdatePageCount(val newPageCount:String): MainAction

    data class UpdateLineCount(val newLineCount:String): MainAction

    data class UpdateSymbols(val newSymbolCount:String): MainAction

    data object ToggleVisibility: MainAction
}


/***
 *
 */
sealed interface MainEffect: Effect {

    data class ShowToast(val message: String): MainEffect
}
