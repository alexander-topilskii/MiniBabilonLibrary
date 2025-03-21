package com.ato.minibabilonlibrary.nano

import java.math.BigInteger

class BabylonLibrary(
    private val alphabet: String,
    private val pages: Int,
    private val linesPerPage: Int,
    private val symbolsPerLine: Int,
    private val maxRoom: BigInteger,
    private val maxWall: BigInteger,
    private val maxShelf: BigInteger,
    private val maxVolume: BigInteger
) {
    private val bookSize = pages * linesPerPage * symbolsPerLine // Полный размер книги
    private val totalBooks = maxRoom * maxWall * maxShelf * maxVolume // Общее количество книг

    fun getBook(
        room: BigInteger,
        wall: BigInteger,
        shelf: BigInteger,
        volume: BigInteger
    ): List<List<String>> {
        val index = getIndex(room, wall, shelf, volume)
        val content = generateBookContent(index)
        return content.chunked(symbolsPerLine).chunked(linesPerPage)
    }

    fun printBook(room: BigInteger, wall: BigInteger, shelf: BigInteger, volume: BigInteger) {
        val book = getBook(room, wall, shelf, volume)
        println("Book (Room: $room, Wall: $wall, Shelf: $shelf, Volume: $volume)")
        book.forEachIndexed { pageNum, page ->
            println("\n--- Page ${pageNum + 1} ---")
            page.forEach { println(it) }
        }
    }

    private fun getIndex(
        room: BigInteger,
        wall: BigInteger,
        shelf: BigInteger,
        volume: BigInteger
    ): BigInteger {
        require(room < maxRoom && wall < maxWall && shelf < maxShelf && volume < maxVolume) { "Invalid book location" }
        return (((room * maxWall + wall) * maxShelf + shelf) * maxVolume + volume)
    }

    private fun generateBookContent(index: BigInteger): String {
        val base = BigInteger.valueOf(alphabet.length.toLong())
        var num = index
        val content = StringBuilder()
        repeat(bookSize) {
            content.append(alphabet[(num % base).toInt()])
            num /= base
        }
        return content.toString().reversed() // Reverse to maintain correct order
    }

    fun findBookByContent(query: String): Quadruple<BigInteger, BigInteger, BigInteger, BigInteger>? {

        val maxIndex = totalBooks

        var index = BigInteger.ZERO
        while (index < maxIndex) {
            val content = generateBookContent(index)
            if (query in content) {
                val (room, wall, shelf, volume) = getLocation(index)
                return Quadruple(room, wall, shelf, volume)
            }
            index++
        }
        return null
    }

    private fun getLocation(index: BigInteger): Quadruple<BigInteger, BigInteger, BigInteger, BigInteger> {
        var num = index
        val volume = num % maxVolume
        num /= maxVolume
        val shelf = num % maxShelf
        num /= maxShelf
        val wall = num % maxWall
        num /= maxWall
        val room = num
        return Quadruple(room, wall, shelf, volume)
    }
}

data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

fun main() {
    val library = BabylonLibrary(
        alphabet = "0123456789",
        pages = 1,
        linesPerPage = 1,
        symbolsPerLine = 10,
        maxRoom = BigInteger.TEN.pow(100),
        maxWall = BigInteger.TEN.pow(100),
        maxShelf = BigInteger.TEN.pow(100),
        maxVolume = BigInteger.TEN.pow(100)
    )

    val query = "0000500000"
    val bookLocation = library.findBookByContent(query)
    if (bookLocation != null) {
        println("Found book with '$query' at Room: ${bookLocation.first}, Wall: ${bookLocation.second}, Shelf: ${bookLocation.third}, Volume: ${bookLocation.fourth}")
    } else {
        println("No book found containing '$query'")
    }

    library.printBook(bookLocation!!.first, bookLocation.second, bookLocation.third, bookLocation.fourth)

    library.printBook(BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(10000))

}
