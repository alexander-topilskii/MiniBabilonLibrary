package com.ato.minibabilonlibrary.main_mvi.logic

object LibraryParamsCalculator {
    fun calculateBookCount(
        alphabet: String,
        line: String,
        symbolsOnLine: String,
        page: String
    ): Pair<Int, Int> {

        val alphabetSize = alphabet.length
        val pageSize = page.toInt()
        val lineSize = line.toInt()
        val symbolsOnLineSize = symbolsOnLine.toInt()

        return countBooks(
            alphabetSize,
            pageSize,
            lineSize,
            symbolsOnLineSize
        )
    }

   private fun countBooks(alphabet: Int, page: Int, line: Int, symbols: Int): Pair<Int, Int> {
        val bookSize = page * line * symbols

        return alphabet to bookSize
    }
}