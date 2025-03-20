package com.ato.minibabilonlibrary.main_mvi.ui

import com.ato.minibabilonlibrary.main_mvi.elements.MainState

object MainUiMapper {
   operator fun invoke(state: MainState): UiMainState {
        return UiMainState(
            alphabet = state.alphabet,
            page = state.pageCount.toString(),
            line = state.linesOnPage.toString(),
            symbolsOnLine = state.symbolsOnLine.toString(),
            isSettingsVisible = state.isSettingsVisible,
            libraryInfo = "Book contains 2 pages\n" +
                    "10 lines on each page\n" +
                    "20 symbols in each line.\n" +
                    "Total books count: ${state.booksCountInLibrary} ",
            topText = "Bibel Library"
        )
    }

}