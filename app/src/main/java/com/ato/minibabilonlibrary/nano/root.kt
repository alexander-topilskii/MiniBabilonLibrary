package com.ato.minibabilonlibrary.nano

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

// вычисляем корень заданной степени с нужной точностью
fun root(number: Int, rootDegree: Int, precision: Int): BigDecimal {
    val context = MathContext(precision + 10, RoundingMode.HALF_UP)
    val bdNumber = BigDecimal(number, context)
    val exponent = BigDecimal.ONE.divide(BigDecimal(rootDegree), context)
    return bdNumber.pow(exponent.toInt(), context).setScale(precision, RoundingMode.HALF_UP)
}

// поиск подстроки в десятичной записи корня
fun compressNumber(target: String, numberUnderRoot: Int, rootDegree: Int, precision: Int): Triple<Int, Int, Int>? {
    val rootStr = root(numberUnderRoot, rootDegree, precision).toPlainString().substringAfter(".")
    val index = rootStr.indexOf(target)
    return if (index != -1) Triple(numberUnderRoot, rootDegree, index) else null
}

// восстановление числа по сжатому представлению
fun decompressNumber(numberUnderRoot: Int, rootDegree: Int, offset: Int, length: Int, precision: Int): String {
    val rootStr = root(numberUnderRoot, rootDegree, precision).toPlainString().substringAfter(".")
    return rootStr.substring(offset, offset + length)
}

// пример использования
fun main() {
    val number = "19615242270663188058233"
    val numberUnderRoot = 30
    val rootDegree = 2
    val precision = 100000  // точность вычисления корня

    val compressed = compressNumber(number, numberUnderRoot, rootDegree, precision)

    if (compressed != null) {
        println("Сжатое представление: (число под корнем: ${compressed.first}, степень: ${compressed.second}, смещение: ${compressed.third}, длина: ${number.length})")

        // проверим восстановление
        val decompressed = decompressNumber(
            compressed.first,
            compressed.second,
            compressed.third,
            number.length,
            precision
        )

        println("Восстановленное число: $decompressed")
    } else {
        println("Не удалось найти число в указанном корне.")
    }
}
