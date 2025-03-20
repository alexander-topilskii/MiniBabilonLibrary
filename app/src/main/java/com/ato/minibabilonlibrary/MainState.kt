package com.ato.minibabilonlibrary

import com.ato.mvicore.State


data class MainState(
    val topText: String = DEFAULT_STATE.topText,
    val libraryInfo: String = DEFAULT_STATE.libraryInfo,
    val alphabet: String = DEFAULT_STATE.alphabet,
    val page: String = DEFAULT_STATE.page,
    val line: String = DEFAULT_STATE.line,
    val symbols: String = DEFAULT_STATE.symbols,
    val isSettingsVisible: Boolean = DEFAULT_STATE.isSettingsVisible
) : State {

    companion object {
        val DEFAULT_STATE = MainState(
            topText = "Bibel Library",
            libraryInfo = "Book contains 2 pages\n" +
                    "10 lines on each page\n" +
                    "20 symbols in each line",
            alphabet = "0123456789",
            page = "2",
            line = "10",
            symbols = "20",
            isSettingsVisible = false
        )
    }
}


//алфавит 10 символов (0123456789) без знаков препинания и пробелов
//книга содержит 2 страницы
//на каждой странице 10 строк
//в строке 20 символов