package com.ato.minibabilonlibrary.mvi.user_actions



interface UserAction: Action

data class UpdateAlphabet(val newAlphabet: String): UserAction

data class UpdatePageCount(val newPageCount:String): UserAction

data class UpdateLineCount(val newLineCount:String): UserAction

data class UpdateSymbols(val newSymbolCount:String): UserAction

object ToggleVisibility: UserAction