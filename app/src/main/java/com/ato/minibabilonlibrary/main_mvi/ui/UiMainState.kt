package com.ato.minibabilonlibrary.main_mvi.ui


data class UiMainState(
    val topText: String,
    val settingsStateUi: SettingsStateUi
) {

    companion object {
        val DEFAULT_STATE = UiMainState(
            topText = "Bibel Library",
            settingsStateUi = SettingsStateUi.DEFAULT_STATE
        )
    }
}

data class ParamsCount(
    val base: Int,
    val pow: Int
)

data class SettingsStateUi(
    val settingsTitle: String,
    val alphabet: String,
    val libraryInfo: String,
    val page: String,
    val line: String,
    val symbolsOnLine: String,
    val isSettingsVisible: Boolean,
    val booksCountBase: String,
    val booksCountPow: String
) {
    companion object {
        val DEFAULT_STATE = SettingsStateUi(
            settingsTitle = "Settings",
            alphabet = "0123456789",
            page = "2",
            line = "10",
            symbolsOnLine = "20",
            isSettingsVisible = false,
            libraryInfo = "Book contains 2 pages\n" +
                    "10 lines on each page\n" +
                    "20 symbols in each line.\n" +
                    "Total books count:",
            booksCountBase = 10.toString(),
            booksCountPow = 400.toString()
        )
    }
}
