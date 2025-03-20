package com.ato.minibabilonlibrary.main_mvi.elements

import com.ato.mvicore.State
import java.math.BigInteger


data class MainState(
    val alphabet: String = DEFAULT_STATE.alphabet,
    val pageCount: Int = DEFAULT_STATE.pageCount,
    val linesOnPage: Int = DEFAULT_STATE.linesOnPage,
    val symbolsOnLine: Int = DEFAULT_STATE.symbolsOnLine,
    val booksCountInLibrary: BigInteger = DEFAULT_STATE.booksCountInLibrary,
    val isSettingsVisible: Boolean = DEFAULT_STATE.isSettingsVisible
) : State {

    companion object {
        val DEFAULT_STATE = MainState(
            alphabet = "0123456789",
            pageCount = 2,
            linesOnPage = 10,
            symbolsOnLine = 20,
            isSettingsVisible = false,
            booksCountInLibrary = BigInteger.valueOf(100)
        )
    }
}
