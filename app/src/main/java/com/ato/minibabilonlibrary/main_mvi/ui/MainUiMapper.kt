package com.ato.minibabilonlibrary.main_mvi.ui

import com.ato.minibabilonlibrary.main_mvi.elements.MainState

object MainUiMapper {
   operator fun invoke(state: MainState): UiMainState {
        return UiMainState(
            settingsStateUi = SettingsStateUi(
                settingsTitle = "Settings",
                alphabet = state.alphabet,
                page = state.pageCount.toString(),
                line = state.linesOnPage.toString(),
                symbolsOnLine = state.symbolsOnLine.toString(),
                isSettingsVisible = state.isSettingsVisible,
                libraryInfo = "Book contains ${state.pageCount} pages\n" +
                        "${state.linesOnPage} lines on each page\n" +
                        "${state.symbolsOnLine} symbols in each line.\n" +
                        "Total books count: ",
                booksCountBase = state.baseBooksCountInLibrary.toString(),
                booksCountPow = state.powBooksCountInLibrary.toString()
            ),
            topText = "Bibel Library"
        )
    }
}