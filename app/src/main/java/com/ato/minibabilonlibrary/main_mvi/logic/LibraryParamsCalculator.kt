package com.ato.minibabilonlibrary.main_mvi.logic

import java.math.BigInteger

object LibraryParamsCalculator {
    fun calculateBookCount(
        alphabet: String,
        line: String,
        symbolsOnLine: String,
        page: String
    ): BigInteger {
        println("ALEX: $alphabet, $line, $symbolsOnLine, $page")

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

   private fun countBooks(alphabet: Int, page: Int, line: Int, symbols: Int): BigInteger {
        val bookSize = page * line * symbols

        return BigInteger.valueOf(alphabet.toLong()).pow(bookSize)
    }
}